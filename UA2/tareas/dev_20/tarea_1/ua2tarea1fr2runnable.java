package tarea_1;

import java.util.ArrayList;
import java.util.List;

public class ua2tarea1fr2runnable {
  public static int contador;
  public static void main(String[] args) {
    List<Thread> listaHilos = new ArrayList<>();

    for(int i = 0; i<5; i++){
      listaHilos.add(new Thread(new HiloThreadSinSincronizar()));
    }

    for(Thread hilo: listaHilos){
      hilo.start();
    }

    for(Thread hilo: listaHilos){
      try{
        hilo.join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public synchronized static void incrementar(int valor){
    contador += valor;
    System.out.println(contador);
  }
  // En este caso implemento Runnable en vez de extender de Thread
  public static class HiloThreadSinSincronizar implements Runnable {
    @Override
    public void run() {
      incrementar(1000);
    }

  }
}

