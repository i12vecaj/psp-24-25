import java.util.Random;

public class Temperatura implements Runnable {
  String nombre = "Temperatura";
  @Override
  public void run(){
    Random aleatorio = new Random();

    for(int i = 0; i < 10; i++){
      int temperatura = aleatorio.nextInt(40);
      System.out.println(System.currentTimeMillis()+" -- "+nombre+" -- "+"Valor: "+temperatura+" ºC");
      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){
        System.out.println("Tenemos un problema");
      }
    }
    System.out.println("Lectura de "+nombre+" completada");

  }
}
