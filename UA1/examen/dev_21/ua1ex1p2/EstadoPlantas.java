import java.util.Random;

public class EstadoPlantas implements Runnable{
  String nombre = "Estado de Plantas";

  @Override
  public void run(){
    Random aleatorio = new Random();

    for(int i = 0; i < 10; i++){
      int estadoPlanta = aleatorio.nextInt(100);
      System.out.println(System.currentTimeMillis()+" -- "+nombre+" -- "+"Valor: "+estadoPlanta+"%");
      try{
        Thread.sleep(1000);
      }catch(InterruptedException e){
        System.out.println("Tenemos un problema");
      }
    }
    System.out.println("Lectura de "+nombre+" completada");

  }
}
