package ua1ex1p2;

public class Main {
  public static void main(String[] args) throws InterruptedException {

    HiloTemperatura hiloTemperatura = new HiloTemperatura();
    Thread threadTemperatura = new Thread(hiloTemperatura);
    threadTemperatura.start();
    threadTemperatura.join();

    HiloHumedad hiloHumedad = new HiloHumedad();
    Thread threadHumedad = new Thread(hiloHumedad);
    threadHumedad.start();
    threadHumedad.join();

    HiloEstadoPlantas hiloEstado = new HiloEstadoPlantas();
    Thread threadEstado = new Thread(hiloEstado);
    threadEstado.start();
    threadEstado.join();







  }
}
