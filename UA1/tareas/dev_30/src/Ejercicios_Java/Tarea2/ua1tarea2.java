package Ejercicios_Java.Tarea2;

public class ua1tarea2 extends Thread  {
    public static void main(String[] args) {
        Thread hilo = new Thread(new Hilo1());
        hilo.start();
    }
}
