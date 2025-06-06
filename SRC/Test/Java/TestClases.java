import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class TestSuite {

    @Test
    void testUsuarioCreation() {
        Usuario usuario = new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234");
        assertEquals("U001", usuario.getId());
        assertEquals("Carlos Pérez", usuario.getNombre());
        assertEquals("carlos@mail.com", usuario.getCorreo());
        assertEquals("1234", usuario.getContraseña());
    }

    @Test
    void testCompareToUsuario() {
        Usuario u1 = new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234");
        Usuario u2 = new Usuario("U002", "Lucía García", "lucia@mail.com", "abcd");
        assertTrue(u1.compareTo(u2) < 0);
    }

    @Test
    void testListarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        lista.add(new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234"));
        lista.add(new Usuario("U002", "Lucía García", "lucia@mail.com", "abcd"));
        assertEquals(2, lista.size());
    }

    @Test
    void testRegistrarUsuarioSinDuplicados() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        Usuario u1 = new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234");
        Usuario u2 = new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234"); // Mismo ID
        Main.registrarUsuario(usuarios, u1);
        Main.registrarUsuario(usuarios, u2);
        assertEquals(1, usuarios.size());
    }

    @Test
    void testEventoCreation() {
        Organizador o1 = new Organizador("O001", "Ana López", "ana@mail.com", 123456789);
        Categoria c1 = new Categoria("C001", "Conferencia");
        Ubicacion ub1 = new Ubicacion("L001", "Calle Mayor 5", "Madrid", "España");
        Evento evento = new Evento("E001", "2025-06-10", 2, "Charla sobre IA", o1, c1, ub1);
        assertEquals("E001", evento.getId());
        assertEquals("2025-06-10", evento.getFecha());
    }

    @Test
    void testListarEventos() {
        ArrayList<Evento> lista = new ArrayList<>();
        Organizador o1 = new Organizador("O001", "Ana López", "ana@mail.com", 123456789);
        Categoria c1 = new Categoria("C001", "Conferencia");
        Ubicacion ub1 = new Ubicacion("L001", "Calle Mayor 5", "Madrid", "España");
        lista.add(new Evento("E001", "2025-06-10", 2, "Charla sobre IA", o1, c1, ub1));
        assertEquals(1, lista.size());
    }

    @Test
    void testInscribirUsuario() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        Usuario u1 = new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234");
        Organizador o1 = new Organizador("O001", "Ana López", "ana@mail.com", 123456789);
        Categoria c1 = new Categoria("C001", "Conferencia");
        Ubicacion ub1 = new Ubicacion("L001", "Calle Mayor 5", "Madrid", "España");
        Evento e1 = new Evento("E001", "2025-06-10", 2, "Charla sobre IA", o1, c1, ub1);
        Inscripcion i1 = new Inscripcion("I001", "2025-06-01", u1, e1);
        Main.inscribirUsuario(inscripciones, i1);
        assertEquals(1, inscripciones.size());
    }

    @Test
    void testInscripcionDuplicada() {
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();
        Usuario u1 = new Usuario("U001", "Carlos Pérez", "carlos@mail.com", "1234");
        Organizador o1 = new Organizador("O001", "Ana López", "ana@mail.com", 123456789);
        Categoria c1 = new Categoria("C001", "Conferencia");
        Ubicacion ub1 = new Ubicacion("L001", "Calle Mayor 5", "Madrid", "España");
        Evento e1 = new Evento("E001", "2025-06-10", 2, "Charla sobre IA", o1, c1, ub1);
        Inscripcion i1 = new Inscripcion("I001", "2025-06-01", u1, e1);
        Inscripcion i2 = new Inscripcion("I002", "2025-06-01", u1, e1); // Mismo usuario en mismo evento
        Main.inscribirUsuario(inscripciones, i1);
        Main.inscribirUsuario(inscripciones, i2);
        assertEquals(1, inscripciones.size());
    }

    @Test
    void testRegistrarUbicacionSinDuplicados() {
        ArrayList<Ubicacion> ubicaciones = new ArrayList<>();
        Ubicacion ub1 = new Ubicacion("L001", "Calle Mayor 5", "Madrid", "España");
        Ubicacion ub2 = new Ubicacion("L001", "Calle Mayor 5", "Madrid", "España"); // Mismo ID
        Main.registrarUbicacion(ubicaciones, ub1);
        Main.registrarUbicacion(ubicaciones, ub2);
        assertEquals(1, ubicaciones.size());
    }

    @Test
    void testRegistrarOrganizadorSinDuplicados() {
        ArrayList<Organizador> organizadores = new ArrayList<>();
        Organizador o1 = new Organizador("O001", "Ana López", "ana@mail.com", 123456789);
        Organizador o2 = new Organizador("O001", "Ana López", "ana@mail.com", 123456789); // Mismo ID
        Main.registrarOrganizador(organizadores, o1);
        Main.registrarOrganizador(organizadores, o2);
        assertEquals(1, organizadores.size());
    }
}