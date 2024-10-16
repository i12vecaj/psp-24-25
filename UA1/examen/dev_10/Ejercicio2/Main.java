import java.util.Random;
import java.util.random.*;

public class Main {
  public static void main(String[] args) {
    Random aleatorio = new Random();
    // Crear una instancia de la clase Runnable
    hiloTemperatura hilo1 = new hiloTemperatura(aleatorio.nextInt(100+1)-(-257));
    Thread thread = new Thread(hilo1);
    thread.start();
    

    try {
      thread.join();//Espero a que termine el hilo
    } catch (InterruptedException e) {
        System.out.println("Hilo interrumpido");
      }
      System.out.println("Fin del programa");


    hiloEstadoPlantas hilo3 = new hiloEstadoPlantas(aleatorio.nextInt(100+1)-0);
    Thread thread3 = new Thread(hilo3);
    thread3.start();

    try {
      thread3.join();//Espero a que termine el hilo
    } catch (InterruptedException e) {
      System.out.println("Hilo interrumpido");
    }
    System.out.println("Fin del programa");

    
    hiloHumedad hilo2 = new hiloHumedad(aleatorio.nextInt(100+1)-0);
    Thread thread2 = new Thread(hilo2);
    thread2.start();

    try {
      thread2.join();//Espero a que termine el hilo
    } catch (InterruptedException e) {
      System.out.println("Hilo interrumpido");
    }
    System.out.println("Fin del programa");
  }
}



