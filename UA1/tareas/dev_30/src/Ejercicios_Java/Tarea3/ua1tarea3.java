package Ejercicios_Java.Tarea3;

public class ua1tarea3 {
    public static void main(String[] args) {
        //Declaro el hilo
        Thread hilo = new Thread(new HiloNumeros(args));
        hilo.start();

    }
}
