package tarea_1;

import java.util.ArrayList;
import java.util.List;

public class ua2tarea1fr2{
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
  // En este caso uso un método sincronizado que es llamado en el hilo
  public synchronized static void incrementar(int valor){
    contador += valor;
    System.out.println(contador);
  }
  public static class HiloThreadSinSincronizar extends Thread{
    @Override
    public void run() {
      incrementar(1000);
    }

  }
}
