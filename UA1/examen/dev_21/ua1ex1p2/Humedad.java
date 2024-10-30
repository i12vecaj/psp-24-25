import java.util.Random;

public class Humedad implements Runnable {
  String nombre = "Humedad";
  @Override
  public void run(){
    Random aleatorio = new Random();

    for(int i = 0; i < 10; i++){
      int humedad = aleatorio.nextInt(50);
      System.out.println(System.currentTimeMillis()+" -- "+nombre+" -- "+"Valor: "+humedad+" g/m^3");
      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){
        System.out.println("Tenemos un problema");
      }
    }
    System.out.println("Lectura de "+nombre+" completada");

  }
}
