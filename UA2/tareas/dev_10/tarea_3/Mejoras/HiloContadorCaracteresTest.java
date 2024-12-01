package Mejoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HiloContadorCaracteresTest {

  private String archivoTest;

  @BeforeEach
  public void setUp() throws IOException {
    // Creamos un archivo temporal para los tests
    archivoTest = "testFile.txt";
    try (FileWriter writer = new FileWriter(archivoTest)) {
      writer.write("AulaDeSoftwareLibreFPCordoba"); // Escribimos algunos caracteres
    }
  }

  @Test
  public void testContadorDeCaracteres() {
    HiloContadorCaracteres hiloContador = new HiloContadorCaracteres(archivoTest);
    Thread hilo = new Thread(hiloContador);
    try {
      hilo.start();
      hilo.join(); // Esperamos a que el hilo termine
    } catch (InterruptedException e) {
      fail("El hilo fue interrumpido: " + e.getMessage());
    }

    // Verificamos que el número de caracteres sea correcto
    assertEquals(26, hiloContador.getContadorCaracteres(), "El contador de caracteres no es correcto");
  }

  @Test
  public void testArchivoVacio() throws IOException {
    // Creamos un archivo vacío para probar el contador
    String archivoVacio = "testFileVacio.txt";
    new File(archivoVacio).createNewFile();

    HiloContadorCaracteres hiloContador = new HiloContadorCaracteres(archivoVacio);
    Thread hilo = new Thread(hiloContador);
    try {
      hilo.start();
      hilo.join();
    } catch (InterruptedException e) {
      fail("El hilo fue interrumpido: " + e.getMessage());
    }

    assertEquals(0, hiloContador.getContadorCaracteres(), "El contador de caracteres debería ser 0 para un archivo vacío");
  }

  @Test
  public void testArchivoNoExistente() {
    HiloContadorCaracteres hiloContador = new HiloContadorCaracteres("archivoInexistente.txt");
    Thread hilo = new Thread(hiloContador);
    try {
      hilo.start();
      hilo.join();
    } catch (InterruptedException e) {
      fail("El hilo fue interrumpido: " + e.getMessage());
    }

    // Si el archivo no existe, el contador debería seguir siendo 0
    assertEquals(0, hiloContador.getContadorCaracteres(), "El contador de caracteres debería ser 0 para un archivo inexistente");
  }
}
