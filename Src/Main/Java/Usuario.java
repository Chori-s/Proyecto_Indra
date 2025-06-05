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
        boolean yaInscrito = false;
        for (int i = 0; i < eventosInscritos.size(); i++) {
            if (eventosInscritos.get(i).getId() == evento.getId()) {
                yaInscrito = true;
                break;
            }
        }
        if (!yaInscrito) {
            eventosInscritos.add(evento);
            evento.agregarInscrito(this); 
            System.out.println("Inscripción a '" + evento.getNombre() + "' realizada.");
        } else {
            System.out.println("Ya estabas inscrito en este evento.");
        }
    }

    public void cancelarInscripcion(Evento evento) {
        for (int i = 0; i < eventosInscritos.size(); i++) {
            if (eventosInscritos.get(i).getId() == evento.getId()) {
                eventosInscritos.remove(i);
                evento.quitarInscrito(this); // Bidireccionalidad
                System.out.println("Inscripción cancelada.");
                return;
            }
        }
        System.out.println("No estabas inscrito en este evento.");
    }

    public ArrayList<Evento> getEventosInscritos() {
        return eventosInscritos; 
    }

    public static void guardarUsuarios(ArrayList<Usuario> usuarios, String rutaArchivo) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(rutaArchivo));
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                pw.println(u.id + "," + u.nombre + "," + u.email + "," + u.contrasena);
            }
            pw.close();
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    // Método para mostrar info básica
    public String toString() {
        return nombre + " (ID: " + id + ")";
    }
}