import java.io.*;
import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String contrasena;
    private ArrayList<Evento> eventosInscritos;

    public Usuario(int id, String nombre, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.eventosInscritos = new ArrayList<Evento>();
    }

    public void inscribirse(Evento evento) {
        boolean encontrado = false;
        for (int i = 0; i < eventosInscritos.size(); i++) {
            if (eventosInscritos.get(i).getId() == evento.getId()) {
                encontrado = true;
                break;
            }
        }
        
        if (!encontrado) {
            eventosInscritos.add(evento);
            System.out.println(nombre + " se ha inscrito en: " + evento.getNombre());
        } else {
            System.out.println(nombre + " ya estaba inscrito en este evento.");
        }
    }

    public void cancelarInscripcion(Evento evento) {
        for (int i = 0; i < eventosInscritos.size(); i++) {
            if (eventosInscritos.get(i).getId() == evento.getId()) {
                eventosInscritos.remove(i);
                System.out.println("Inscripción cancelada correctamente.");
                return;
            }
        }
        System.out.println("No estabas inscrito en este evento.");
    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios, String rutaArchivo) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(rutaArchivo));
            
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                String linea = u.id + "," + u.nombre + "," + u.email + "," + u.contrasena;
                
                linea += ",";
                for (int j = 0; j < u.eventosInscritos.size(); j++) {
                    if (j > 0) linea += ";";
                    linea += u.eventosInscritos.get(j).getId();
                }
                
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Usuarios guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo: " + e.getMessage());
            }
        }
    }

    public static ArrayList<Usuario> cargarUsuarios(String rutaArchivo, ArrayList<Evento> todosEventos) {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        BufferedReader br = null;
        
        try {
            br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 4) {
                    Usuario u = new Usuario(
                        Integer.parseInt(partes[0]),
                        partes[1],
                        partes[2],
                        partes[3]
                    );
                    
                    if (partes.length > 4 && !partes[4].isEmpty()) {
                        String[] idsEventos = partes[4].split(";");
                        for (int i = 0; i < idsEventos.length; i++) {
                            int idEvento = Integer.parseInt(idsEventos[i]);
                            for (int j = 0; j < todosEventos.size(); j++) {
                                if (todosEventos.get(j).getId() == idEvento) {
                                    u.inscribirse(todosEventos.get(j));
                                    break;
                                }
                            }
                        }
                    }
                    usuarios.add(u);
                }
            }
            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar archivo: " + e.getMessage());
            }
        }
        return usuarios;
    }

    // Getters básicos
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public ArrayList<Evento> getEventosInscritos() { return eventosInscritos; }

    // Método para mostrar información
    public String toString() {
        return "ID: " + id + " - " + nombre + " (" + email + ")";
    }
}