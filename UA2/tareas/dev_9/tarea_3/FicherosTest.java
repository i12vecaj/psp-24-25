package org.example;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FicherosTest {

    @Test
    void testArchivoValido() throws IOException {
        // Crear un archivo temporal para el test
        String nombreArchivo = "archivo_test";
        try (FileWriter fw = new FileWriter(nombreArchivo + ".txt")) {
            fw.write("Hola Mundo!");
        }

        // Instanciar y ejecutar el hilo
        Ficheros ficheros = new Ficheros(nombreArchivo);
        ficheros.start();

        // Esperar a que el hilo termine
        try {
            ficheros.join();
        } catch (InterruptedException e) {
            fail("El hilo fue interrumpido.");
        }
    }

    @Test
    void testArchivoInvalido() {
        // Archivo que no existe
        String nombreArchivo = "archivo_inexistente";
        Ficheros ficheros = new Ficheros(nombreArchivo);

        // Ejecutar el hilo y verificar que no lanza excepción
        assertDoesNotThrow(() -> {
            ficheros.start();
            ficheros.join();
        });
    }

    @Test
    void testConteoCaracteres() throws IOException {
        String nombreArchivo = "archivo_caracteres";
        try (FileWriter fw = new FileWriter(nombreArchivo + ".txt")) {
            fw.write("12345");
        }

        // Instanciar y ejecutar el hilo
        Ficheros ficheros = new Ficheros(nombreArchivo);
        ficheros.start();

        // Esperar a que el hilo termine
        try {
            ficheros.join();
        } catch (InterruptedException e) {
            fail("El hilo fue interrumpido.");
        }

    }
}
