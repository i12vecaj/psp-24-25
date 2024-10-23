package ua1ex1p2;

import java.util.Random;

public class HiloTemperatura implements Runnable{
  public int exitCode;
  Random random = new Random();
  @Override
  public void run() {
    try{
      // Bucle para que mide tres veces
      for(int i = 0; i<10; i++){
        // Le pongo un valor aleatoria
        int temperatura = random.nextInt(100);;
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        // Muesto la información
        System.out.println("La temperatura es de: " + temperatura + "º" );
        System.out.println("Lectura echa en " + System.currentTimeMillis());
      }
      System.out.println("Terminado hilo de temperatura");
      exitCode = 0; //Devuelvo 0 para los test
    }catch (Exception e){
      exitCode = 1;
    }
  }
}
