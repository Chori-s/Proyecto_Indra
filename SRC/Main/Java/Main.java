import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        ArrayList<Organizador> organizadores = new ArrayList<Organizador>();

        Scanner sc = new Scanner(System.in);

        // Crear organizador de ejemplo
        Organizador organizador = new Organizador(1, "EcoOrg");
        organizadores.add(organizador);

        // Crear evento de ejemplo
        Categoria cat = new Categoria("Reciclaje");
        Ubicacion ubi = new Ubicacion("Parque Central");
        Evento evento1 = new Evento(1, "Taller de reciclaje", "2025-06-15", organizador.getNombre(), cat, ubi);
        eventos.add(evento1);

        // Registro del usuario
        System.out.println("=== Registro de Usuario ===");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        Usuario usuarioActual = new Usuario(1, nombre, email, contrasena);
        usuarios.add(usuarioActual);

        int opcion = 0;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ver eventos disponibles");
            System.out.println("2. Inscribirse a un evento");
            System.out.println("3. Cancelar inscripción");
            System.out.println("4. Ver mis inscripciones");
            System.out.println("5. Crear nuevo evento (organizador)");
            System.out.println("6. Eliminar evento (organizador)");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {
                System.out.println("\nEventos disponibles:");
                for (int i = 0; i < eventos.size(); i++) {
                    Evento e = eventos.get(i);
                    System.out.println("ID: " + e.getId() + " - " + e.getNombre() + " - " + e.getFecha()
                            + " - Organizador: " + e.getOrganizador()
                            + " - Categoría: " + e.getCategoria().getNombre()
                            + " - Ubicación: " + e.getUbicacion().getDireccion());
                }

            } else if (opcion == 2) {
                System.out.print("ID del evento: ");
                int idIns = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < eventos.size(); i++) {
                    Evento e = eventos.get(i);
                    if (e.getId() == idIns) {
                        usuarioActual.inscribirse(e);
                        e.agregarInscrito(usuarioActual);
                        System.out.println("Inscripción completada.");
                        break;
                    }
                }

            } else if (opcion == 3) {
                System.out.print("ID del evento a cancelar: ");
                int idCancel = Integer.parseInt(sc.nextLine());
                for (int i = 0; i < eventos.size(); i++) {
                    Evento e = eventos.get(i);
                    if (e.getId() == idCancel) {
                        usuarioActual.cancelarInscripcion(e);
                        e.quitarInscrito(usuarioActual);
                        System.out.println("Inscripción cancelada.");
                        break;
                    }
                }

            } else if (opcion == 4) {
                ArrayList<Evento> inscripciones = usuarioActual.getEventosInscritos();
                System.out.println("\nTus inscripciones:");
                if (inscripciones.size() == 0) {
                    System.out.println("No estás inscrito en ningún evento.");
                } else {
                    for (int i = 0; i < inscripciones.size(); i++) {
                        Evento e = inscripciones.get(i);
                        System.out.println(e.getNombre() + " - " + e.getFecha());
                    }
                }

            } else if (opcion == 5) {
                System.out.println("\n=== Crear Evento ===");
                System.out.print("Nombre del evento: ");
                String nombreEvento = sc.nextLine();
                System.out.print("Fecha (AAAA-MM-DD): ");
                String fecha = sc.nextLine();
                System.out.print("Categoría: ");
                String catNombre = sc.nextLine();
                System.out.print("Ubicación: ");
                String ubic = sc.nextLine();

                int nuevoId = eventos.size() + 1;
                Evento nuevo = new Evento(nuevoId, nombreEvento, fecha, organizador.getNombre(),
                        new Categoria(catNombre), new Ubicacion(ubic));
                eventos.add(nuevo);
                System.out.println("Evento creado correctamente.");

            } else if (opcion == 6) {
                System.out.print("ID del evento a eliminar: ");
                int idEliminar = Integer.parseInt(sc.nextLine());
                boolean encontrado = false;
                for (int i = 0; i < eventos.size(); i++) {
                    Evento e = eventos.get(i);
                    if (e.getId() == idEliminar && e.getOrganizador().equals(organizador.getNombre())) {
                        eventos.remove(i);
                        System.out.println("Evento eliminado.");
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Evento no encontrado o no autorizado.");
                }

            } else if (opcion == 7) {
                System.out.println("Gracias por usar el portal.");
            }

        } while (opcion != 7);

        sc.close();
    }
}