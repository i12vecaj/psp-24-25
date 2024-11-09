package tarea_1;

public class ua2tarea1fr2 {
  public static void main(String[] args) {
    Thread[] hilo = new Thread[5];
    Contador contador = new Contador(0);
    
    for (int i = 0; i < 5; i++) {
        hilo[i] = new Thread(new hilo(contador));
    }
    for (int i = 0; i < 5; i++) {
        hilo[i].start();
    }
    for (int i = 0; i < 5; i++) {
        try{
        hilo[i].join();
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
    synchronized(contador){ // 🤓📋 Usamos el metodo sychronized para sincronizar la variable contador en los hilos por lo que dara (1000, 2000, 3000, 4000 y 5000)
    for (int i = 0; i < 1000; i++) {
        contador.incrementarContador();
    }
    int valorContador = contador.getContador();
    System.out.println("El valor del contador es: " + valorContador);
  }
}
}    
class Contador{

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
