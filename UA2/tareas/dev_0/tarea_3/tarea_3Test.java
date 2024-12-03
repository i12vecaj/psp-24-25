import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.io.*;

public class tarea_3Test {

    @Test
    public void testConteoSecuencialYConcurrente() throws Exception {
        String[] archivos = {"test1.txt", "test2.txt"};

        crearArchivo("test1.txt", "Hola\nMundo");
        crearArchivo("test2.txt", "Java\nJUnit");

        long secuencial = Main.conteoSecuencial(archivos);
        long concurrente = Main.conteoConConcurrencia(archivos);

        assertEquals(secuencial, concurrente);
        assertEquals(18, secuencial);

        new File("test1.txt").delete();
        new File("test2.txt").delete();
    }

    private void crearArchivo(String nombre, String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombre))) {
            writer.write(contenido);
        }
    }
}
