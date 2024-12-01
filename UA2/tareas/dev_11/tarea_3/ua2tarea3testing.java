import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;

public class ua2tarea3testing {

    @Test
    void testArchivoProcesadorCuentaCaracteresCorrectamente() {
        String nombreArchivo = "archivoTest";
        String contenido = "Hola a todos,\nEsta es una prueba para ver que funciona mi ejercicio.";

        try (FileWriter escritor = new FileWriter(nombreArchivo + ".txt")) {
            escritor.write(contenido);
        } catch (IOException e) {
            fail("No se pudo crear el archivo de prueba: " + e.getMessage());
        }

        ua2tarea3.ArchivoProcesador procesador = new ua2tarea3.ArchivoProcesador(nombreArchivo);
        procesador.start();
        try {
            procesador.join();
        } catch (InterruptedException e) {
            fail("Error esperando al hilo: " + e.getMessage());
        }

        int caracteresEsperados = contenido.length();
        assertEquals(caracteresEsperados, procesador.caracteres, "El conteo de caracteres no es correcto.");
    }
}
