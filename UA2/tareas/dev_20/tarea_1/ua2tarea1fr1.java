package tarea_1;

import java.util.ArrayList;
import java.util.List;

public class ua2tarea1fr1 {
  public static int contador;
  public static void main(String[] args) {
    // Creo una lista de hilos para manejarlos de manera más sencilla
    List<Thread> listaHilos = new ArrayList<>();

    // Agrego a la lista los 5 hilos
    for(int i = 0; i<5; i++){
      listaHilos.add(new Thread(new HiloThreadSinSincronizar()));
    }
    // Arranco los 5 hilos
    for(Thread hilo: listaHilos){
        hilo.start();
    }
    // Espero a que terminen
    for(Thread hilo: listaHilos){
      try{
        hilo.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
  public static class HiloThreadSinSincronizar extends Thread{
    @Override
    public void run() {
      contador += 1000;
      System.out.println(contador);
    }
  }
}
