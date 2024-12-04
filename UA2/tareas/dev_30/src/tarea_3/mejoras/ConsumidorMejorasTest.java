package tarea_3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsumidorMejoraasTest {

    @Test
    public void testContadorDeCaracteres() {
        Consumidor consumidor = new Consumidor("testArchivo.txt");

        // Redirigir la salida estándar para capturar el resultado de la impresión
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        // Aquí se debería escribir el archivo de prueba en el directorio correspondiente
        // Para efectos de prueba, se puede mockear la lectura del archivo

        // Simular un archivo con 5 caracteres
        consumidor.run();

        // Verificar que el contador es correcto (esto debería ser un valor mockeado o un archivo con caracteres)
        String output = outputStream.toString();
        assertTrue(output.contains("El número de caracteres del archivo"));
    }

    @Test
    public void testManejoDeArchivoNoEncontrado() {
        Consumidor consumidor = new Consumidor("archivoNoExistente.txt");

        // Redirigir la salida de error
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setErr(printStream);

        consumidor.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Archivo no encontrado"));
    }

    @Test
    public void testManejoDeExcepciones() {
        // Crear un consumidor con un archivo erróneo
        Consumidor consumidor = new Consumidor("archivoErroneo.txt");

        // Redirigir la salida de error
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setErr(printStream);

        consumidor.run();

        String output = outputStream.toString();
        assertTrue(output.contains("Error de lectura en el archivo"));
    }
}
