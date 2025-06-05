import java.util.ArrayList;

public class Evento {
    private int id;
    private String nombre;
    private String fecha;
    private String organizador;
    private Categoria categoria;
    private Ubicacion ubicacion;
    private ArrayList<Usuario> inscritos;

    public Evento(int id, String nombre, String fecha, String organizador, 
                 Categoria categoria, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.organizador = organizador;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.inscritos = new ArrayList<Usuario>(); // Inicialización clásica
    }

    // --- Métodos para inscripciones (con for normales) ---
    public void agregarInscrito(Usuario usuario) {
        boolean yaInscrito = false;
        for (int i = 0; i < inscritos.size(); i++) {
            if (inscritos.get(i).getId() == usuario.getId()) {
                yaInscrito = true;
                break;
            }
        }
        if (!yaInscrito) {
            inscritos.add(usuario);
        }
    }

    public void quitarInscrito(Usuario usuario) {
        for (int i = 0; i < inscritos.size(); i++) {
            if (inscritos.get(i).getId() == usuario.getId()) {
                inscritos.remove(i);
                break;
            }
        }
    }

    // --- Getters básicos ---
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getOrganizador() {
        return organizador;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    // Método para mostrar info básica (útil para depuración)
    public String toString() {
        return "Evento: " + nombre + " (ID: " + id + ")";
    }
}