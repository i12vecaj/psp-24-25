public class Main {
  public static void main(String[] args) {
      Thread sensorTemperatura = new Thread(new hiloTemperatura());
      Thread sensorHumedad = new Thread(new hiloHumedad());
      Thread sensorEstadoPlantas = new Thread(new hiloEstadoPlantas(0));

      sensorTemperatura.start();
      sensorHumedad.start();
      sensorEstadoPlantas.start();

      try {
          sensorTemperatura.join();
          sensorHumedad.join();
          sensorEstadoPlantas.join();
      } catch (InterruptedException e) {
          System.out.println("La ejecución fue interrumpida.");
      }
      System.out.println("El monitoreo del huerto se ha completado.");
  }
}