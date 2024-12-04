package tarea_1.Mejoras;

import java.util.ArrayList;
import java.util.List;

public class ua2tarea1fr2 {

  // Contador compartido
  private static int contador;

  public static void main(String[] args) {
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

  // Método sincronizado para incrementar el contador
  public synchronized static void incrementar(int valor) {
    contador += valor;
    System.out.println(contador);
  }

  public static class HiloThreadSinSincronizar implements Runnable {
    @Override
    public void run() {
      incrementar(1000);
    }
  }
}