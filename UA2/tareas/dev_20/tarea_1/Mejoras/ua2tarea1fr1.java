package tarea_1.Mejoras;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ua2tarea1fr1 {

  // Uso de AtomicInteger para operaciones atómicas sobre el contador
  public static AtomicInteger contador = new AtomicInteger(0);

  public static void main(String[] args) {
    // Lista de hilos
    List<Thread> listaHilos = new ArrayList<>();

    // Creación de 5 hilos
    for (int i = 0; i < 5; i++) {
      listaHilos.add(new Thread(new HiloThreadSinSincronizar()));
    }

    // Iniciar hilos
    listaHilos.forEach(Thread::start);

    // Esperar que terminen
    for (Thread hilo : listaHilos) {
      try {
        hilo.join();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        System.err.println("Hilo interrumpido: " + e.getMessage());
      }
    }
  }

  public static class HiloThreadSinSincronizar implements Runnable {
    @Override
    public void run() {
      // Incremento atómico del contador
      int valor = contador.addAndGet(1000);
      System.out.println(valor);
    }
  }
}