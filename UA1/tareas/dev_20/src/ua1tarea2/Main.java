package ua1tarea2;


public class Main{
    public static void main(String[] args) {
        // Primero instancio un objeto de la clase Thread el cual tiene como argumento en su constructor
        // una instancia del hilo

        Thread hiloDeLosUno = new Thread(new hiloDe1());

        // Arranco el hilo

        hiloDeLosUno.start();
    }
}
