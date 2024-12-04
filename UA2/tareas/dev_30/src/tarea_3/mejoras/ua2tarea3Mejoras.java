package tarea_3.mejoras;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ua2tarea3Mejoras {
    public static void main(String[] args) {
        ArrayList<String> nombresArchivos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String nombreArchivo;

        do {
            System.out.println("Introduzca el nombre del fichero del archivo, para terminar introduzca (*):");
            nombreArchivo = scanner.nextLine();

            if (!nombreArchivo.equals("*")) {
                nombresArchivos.add(nombreArchivo);
            }
        } while (!nombreArchivo.equals("*"));
        scanner.close();

        List<Thread> hilos = new ArrayList<>();
        for (String archivo : nombresArchivos) {
            ConsumidorMejoras consumidor = new ConsumidorMejoras(archivo + ".txt");
            Thread hilo = new Thread(consumidor);
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + hilo.getName());
            }
        }

        System.out.println("Todos los archivos han sido procesados.");
    }
}
