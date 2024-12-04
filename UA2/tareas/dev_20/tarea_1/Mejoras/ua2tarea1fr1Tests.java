package tarea_1.Mejoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;

class ua2tarea1fr1Test {

  @BeforeEach
  void setUp() {
    // Reiniciamos el contador antes de cada prueba
    Ua2Tarea1Fr1.contador.set(0);
  }

  @Test
  void testIncrementoContadorAtomic() throws InterruptedException {
    List<Thread> listaHilos = new ArrayList<>();

    // Creamos 5 hilos que incrementan el contador en 1000
    for (int i = 0; i < 5; i++) {
      listaHilos.add(new Thread(new Ua2Tarea1Fr1.HiloThreadSinSincronizar()));
    }

    // Iniciamos los hilos
    for (Thread hilo : listaHilos) {
      hilo.start();
    }

    // Esperamos a que todos los hilos terminen
    for (Thread hilo : listaHilos) {
      hilo.join();
    }

    // El valor esperado es 5000 porque cada hilo incrementa el contador en 1000
    assertEquals(5000, Ua2Tarea1Fr1.contador.get());
  }
}

class Ua2Tarea1Fr2Test {

  @BeforeEach
  void setUp() {
    // Reiniciamos el contador antes de cada prueba
    Ua2Tarea1Fr2.contador = 0;
  }

  @Test
  void testIncrementoContadorSincronizado() throws InterruptedException {
    List<Thread> listaHilos = new ArrayList<>();

    // Creamos 5 hilos que incrementan el contador en 1000
    for (int i = 0; i < 5; i++) {
      listaHilos.add(new Thread(new Ua2Tarea1Fr2.HiloThreadSinSincronizar()));
    }

    // Iniciamos los hilos
    for (Thread hilo : listaHilos) {
      hilo.start();
    }

    // Esperamos a que todos los hilos terminen
    for (Thread hilo : listaHilos) {
      hilo.join();
    }

    // El valor esperado es 5000 porque cada hilo incrementa el contador en 1000
    assertEquals(5000, Ua2Tarea1Fr2.contador);
  }
}

class Ua2Tarea1Fr2RunnableTest {

  @BeforeEach
  void setUp() {
    // Reiniciamos el contador antes de cada prueba
    Ua2Tarea1Fr2Runnable.contador = 0;
  }

  @Test
  void testIncrementoContadorRunnable() throws InterruptedException {
    List<Thread> listaHilos = new ArrayList<>();

    // Creamos 5 hilos que incrementan el contador en 1000
    for (int i = 0; i < 5; i++) {
      listaHilos.add(new Thread(new Ua2Tarea1Fr2Runnable.HiloThreadSinSincronizar()));
    }

    // Iniciamos los hilos
    for (Thread hilo : listaHilos) {
      hilo.start();
    }

    // Esperamos a que todos los hilos terminen
    for (Thread hilo : listaHilos) {
      hilo.join();
    }

    // El valor esperado es 5000 porque cada hilo incrementa el contador en 1000
    assertEquals(5000, Ua2Tarea1Fr2Runnable.contador);
  }
}