package tarea_1;

public class ua2tarea1fr1{
  public static void main(String[] args) {
      Thread[] hilo = new Thread[5];
      Contador contador = new Contador(0);
      
      for (int i = 0; i < 5; i++) {
          hilo[i] = new Thread(new hilo(contador)); //Creacion de los hilos en array 🧵 
      }
      for (int i = 0; i < 5; i++) {
          hilo[i].start(); // Activando los hilos 🧶
      }
      for (int i = 0; i < 5; i++) {
          try{
          hilo[i].join(); // Esperar hasta que terminen los hilos 🛑 ✋🏻
          }catch(Exception error){
              System.out.println("No ha funcionao" + error);
          }
      }
  }
}
  class hilo implements Runnable{
    Contador contador;
    hilo(Contador contador){  
      this.contador = contador;  
    }

    @Override
    public void run(){ 
          for (int i = 0; i < 1000; i++) { //Nos da Resultado aleatorio🎰 en los hilos por que acceden todos a la vez sin sincronizacion 
              contador.incrementarContador();
          }
          int valorContador = contador.getContador(); //Mostrar el valor de contador 🧮
          System.out.println("El valor del contador es: " + valorContador);
      }
    }    

    class Contador{ //Clase contador en vez de public static int contador para hacer uso de los metodos y a mi parecer es algo mas versatil 🗣️ 🦜

    int contador;

    Contador(int contador){
      this.contador = contador;
    }

    public void incrementarContador(){
      contador++;
    }

    public void decrementarContador(){
      contador--;
    }

    public int getContador(){
      return contador;
    }


}
