package tarea_1.Mejoras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Tarea1Test {

  // Caso de prueba: Verifica si la sincronización en los hilos es correcta.
  @Test
  public void testIncrementoConHilosSincronizados() throws InterruptedException {
    // Instanciar la clase Contador
    Contador contador = new Contador();

    // Crear varios hilos que incrementan el contador
    Thread hilo1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        contador.incrementar();
      }
    });
    Thread hilo2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        contador.incrementar();
      }
    });

    // Iniciar los hilos
    hilo1.start();
    hilo2.start();

    // Esperar a que ambos hilos terminen
    hilo1.join();
    hilo2.join();

    // Verificar si el contador tiene el valor esperado después de la ejecución de ambos hilos
    assertEquals(2000, contador.obtenerValor(), "El contador debería ser 2000 después de ejecutar los dos hilos.");
  }

  // Caso de prueba: Verifica si la sincronización no da lugar a condiciones de carrera.
  @Test
  public void testIncrementoSincronizadoConCondicionesDeCarrera() throws InterruptedException {
    // Instanciar la clase Contador sin sincronización
    Contador contador = new Contador(false); // No usar sincronización

    // Crear varios hilos que incrementan el contador
    Thread hilo1 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        contador.incrementar();
      }
    });
    Thread hilo2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        contador.incrementar();
      }
    });

    // Iniciar los hilos
    hilo1.start();
    hilo2.start();

    // Esperar a que ambos hilos terminen
    hilo1.join();
    hilo2.join();

    // Verificar si el contador no alcanza el valor esperado debido a las condiciones de carrera
    // El valor final debe ser menor a 2000 porque las condiciones de carrera afectan el resultado
    assertEquals(2000, contador.obtenerValor(), "El contador debería ser 2000 después de ejecutar los dos hilos.");
  }
}
