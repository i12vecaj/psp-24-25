package ua1ex1p2;

import java.util.Random;

public class HiloHumedad implements Runnable{
  Random random = new Random();
  public int exitCode;
  @Override
  public void run() {
    try{
      for(int i = 0; i<10; i++) {
        int humedad = random.nextInt(100); // En este caso el valor máximo es 100
        try {
          Thread.sleep(1000); // Espera 1 segundo
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }

        System.out.println("La humedad es de: " + humedad + "%");
        System.out.println("Lectura echa en " + System.currentTimeMillis());
      }
      System.out.println("Terminado el hilo de la humedad");
      exitCode = 0;
    }catch (Exception e){
      exitCode = 1; //Arrojo el codigo 1 para los test
    }
  }
}
