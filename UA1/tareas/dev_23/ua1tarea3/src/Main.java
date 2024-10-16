import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // Crear una instancia del Runnable con los argumentos
        Hilo1 tarea = new Hilo1(args);

        // Crear y ejecutar el hilo
        Thread hilo = new Thread(tarea);
        hilo.start();

        try {
            // Esperar a que el hilo termine antes de continuar
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }
    }
}
