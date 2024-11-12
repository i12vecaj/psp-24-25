public class ua2tarea1fr2 {
  public static void main(String[] args) {
    // Inicializamos el contador.
    Contador contador = new Contador(0);

    //Inicializamos los hilos.
    Thread hilo1 = new Hilo(contador);
    Thread hilo2 = new Hilo(contador);
    Thread hilo3 = new Hilo(contador);
    Thread hilo4 = new Hilo(contador);
    Thread hilo5 = new Hilo(contador);

    //Lanzamos los hilos
    hilo1.start();
    hilo2.start();
    hilo3.start();
    hilo4.start();
    hilo5.start();
    
    //Esperamos que los hilos terminen.

    try{
      hilo1.join();
      hilo2.join();
      hilo3.join();
      hilo4.join();
      hilo5.join();
    }catch(Exception exception){
      
    }
    
  }
}

class  Hilo extends Thread {
  Contador c;
  Hilo(Contador c){
    this.c = c;
  }
  // Creamos la funcion que incrementara el contador y mostramos por pantalla el valor del contador, esta vez lo sincronizamos para que los hilos accedan a la variable adecuadamente.
  @Override
  public void run(){
    synchronized(c) {
      for (int i = 0; i <= 1000; i++){
        c.incrementar();
      }
      System.out.println("El contador tiene el siguiente valor: "+c.getContador());
    }
  }

}


// Clase contador a la que tendran que acceder los hilos.
class Contador {
  int contador;

  Contador(int contador){
    this.contador = contador;
  }

  public void incrementar(){
    contador ++;
  }  

  public int getContador(){
    return contador;
  }
}

/*
 * Resultado:
 * 
 * El contador tiene el siguiente valor: 1001
 * El contador tiene el siguiente valor: 2002
 * El contador tiene el siguiente valor: 3003
 * El contador tiene el siguiente valor: 4004
 * El contador tiene el siguiente valor: 5005
 * 
 * Conclusión:
 * Ahora al sincronizar la clase contador los hilos ya acceden de forma ordenada a la variable y se da este resultado,
 * cada hilo lo incrementa 1001 veces como si lo estuvira llamando una funcion 5 veces.
 */
