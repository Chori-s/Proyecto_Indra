// Clase que representa un organizador de eventos
public class Organizador {
    private int id;
    private String nombre;

    public Organizador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }
}