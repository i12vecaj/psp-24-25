package ua1ex1p2;

public class Main {
  public static void main(String[] args) throws InterruptedException {

    HiloTemperatura hiloTemperatura = new HiloTemperatura();
    Thread threadTemperatura = new Thread(hiloTemperatura);
    threadTemperatura.start();

    HiloHumedad hiloHumedad = new HiloHumedad();
    Thread threadHumedad = new Thread(hiloHumedad);
    threadHumedad.start();

    HiloEstadoPlantas hiloEstado = new HiloEstadoPlantas();
    Thread threadEstado = new Thread(hiloEstado);
    threadEstado.start();

    try{
      threadTemperatura.join();
      threadHumedad.join();
      threadEstado.join();
    }catch (Exception e){
      System.out.println("Ejecución interrumpida: " + e);
    }
  }
}
