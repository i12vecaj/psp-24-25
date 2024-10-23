package ua1ex1p2;

import java.util.Random;

public class HiloEstadoPlantas implements Runnable{
  public int exitCode;
  Random random = new Random();
  @Override
  public void run() {
    try{
      for(int i = 0; i<10; i++) {
        int estado = random.nextInt(100);;
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        System.out.println("El estado es de: " + estado + "%");
        if (estado >= 50) {
          System.out.println("Ya que el estado es mayor o igual a 50% Está en una buena condición");
        } else {
          System.out.println("Ya que el estado es menor al 50% está en una mala condición, se recomienda observación");
        }
        System.out.println("Lectura echa en " + System.currentTimeMillis());
      }
      System.out.println("Terminado el hilo del estado de las plantas");
      exitCode = 0;
    }catch (Exception e){
      exitCode = 1;
    }
  }
}
