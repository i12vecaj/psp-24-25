
public class Hilos {
 static public void main(String[] args){

  Thread temperatura = new Thread(new Temperatura());
  Thread humedad = new Thread(new Humedad());
  Thread estadoPlanta = new Thread(new EstadoPlantas());

  temperatura.start();
  humedad.start();
  estadoPlanta.start();

  try{
    temperatura.join();
    humedad.join();
    estadoPlanta.join();
  }catch(InterruptedException e){
    System.out.println("Tenemos un problema");
  }

 }
  
}
