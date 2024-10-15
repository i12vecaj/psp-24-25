package ua1tarea3;


public class Main{
    public static void main(String[] args){
        Thread hilo = new Thread(new Hilo(args));
        hilo.start();
    }
}