package org.example;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {

    // Helper para crear archivos temporales
    private String createTempFile(String content) throws IOException {
        String tempFilePath = "src/test/resources/tempFile.txt";
        FileWriter writer = new FileWriter(tempFilePath);
        writer.write(content);
        writer.close();
        return tempFilePath;
    }

    @Test
    public void testCaracteresFichero() {
        assertDoesNotThrow(() -> {
            String filePath = createTempFile("Esto es un archivo de prueba.");
            Main.caracteresFichero(filePath);
        });
    }

    @Test
    public void testCaracteresFicheroArchivoInexistente() {
        assertDoesNotThrow(() -> {
            Main.caracteresFichero("archivo_inexistente.txt");
        });
    }

    @Test
    public void testHilo() {
        assertDoesNotThrow(() -> {
            String filePath = createTempFile("Este archivo se leerá en un hilo.");
            Hilo hilo = new Hilo(filePath);
            hilo.start();
            hilo.join(); // Esperamos a que el hilo termine
        });
    }

    @Test
    public void testHiloConArchivoGrande() {
        assertDoesNotThrow(() -> {
            // Crear un archivo grande
            StringBuilder contenido = new StringBuilder();
            for (int i = 0; i < 10000; i++) {
                contenido.append("abcdefghijklmnopqrstuvwxyz\n");
            }
            String filePath = createTempFile(contenido.toString());

            Hilo hilo = new Hilo(filePath);
            hilo.start();
            hilo.join(); // Esperamos a que el hilo termine
        });
    }
}
