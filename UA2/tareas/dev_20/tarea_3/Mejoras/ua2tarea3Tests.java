package tarea_3.mejoras;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

public class LectorDeCharsTest {

  private static final String TEMP_FILE_PATH = "pruebaArchivo.txt";

  @BeforeEach
  public void setUp() throws IOException {
    // Crear un archivo temporal para las pruebas
    String contenido = "Hola, este es un archivo de prueba.";
    Files.write(Paths.get(TEMP_FILE_PATH), contenido.getBytes());
  }

  @AfterEach
  public void tearDown() throws IOException {
    // Borrar el archivo temporal después de las pruebas
    Files.deleteIfExists(Paths.get(TEMP_FILE_PATH));
  }

  @Test
  public void testContadorCaracteres() throws InterruptedException {
    // Crear una instancia del LectorDeChars
    LectorDeChars lector = new LectorDeChars(TEMP_FILE_PATH);
    Thread hilo = new Thread(lector);
    hilo.start();
    hilo.join();

    // Comprobamos que el contador haya contado correctamente los caracteres
    // El archivo "Hola, este es un archivo de prueba." tiene 35 caracteres
    assertEquals(35, lector.contador, "El contador de caracteres no es correcto.");
  }

  @Test
  public void testMultiplesHilos() throws InterruptedException {
    // Crear hilos para leer varios archivos
    LectorDeChars lector1 = new LectorDeChars(TEMP_FILE_PATH);
    LectorDeChars lector2 = new LectorDeChars(TEMP_FILE_PATH);
    LectorDeChars lector3 = new LectorDeChars(TEMP_FILE_PATH);

    Thread hilo1 = new Thread(lector1);
    Thread hilo2 = new Thread(lector2);
    Thread hilo3 = new Thread(lector3);

    hilo1.start();
    hilo2.start();
    hilo3.start();

    hilo1.join();
    hilo2.join();
    hilo3.join();

    // Comprobamos que los tres hilos han contado correctamente los caracteres
    assertEquals(35, lector1.contador, "El contador de caracteres no es correcto en el primer hilo.");
    assertEquals(35, lector2.contador, "El contador de caracteres no es correcto en el segundo hilo.");
    assertEquals(35, lector3.contador, "El contador de caracteres no es correcto en el tercer hilo.");
  }

  @Test
  public void testArchivoNoExistente() throws InterruptedException {
    // Crear una instancia de LectorDeChars con un archivo no existente
    LectorDeChars lector = new LectorDeChars("archivo_no_existente.txt");
    Thread hilo = new Thread(lector);
    hilo.start();

    // Esperamos a que termine el hilo (el archivo no existe, así que debe fallar)
    hilo.join();

    // Al lanzar RuntimeException si el archivo no se encuentra, podemos verificar que el contador sigue siendo 0
    assertEquals(0, lector.contador, "El contador no debe incrementarse si el archivo no existe.");
  }
}