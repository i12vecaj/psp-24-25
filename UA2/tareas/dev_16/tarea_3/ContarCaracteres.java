import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ContarCaracteres {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: No se han proporcionado archivos como argumentos.");
            return;
        }

        long t_comienzoSecuencial = System.currentTimeMillis();
        for (String archivo : args) {
            contarCaracteresSecuencial(archivo);
        }
        long t_finSecuencial = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución secuencial: " + (t_finSecuencial - t_comienzoSecuencial) + " ms");

        // FR2: Ejecución concurrente
        long t_comienzoConcurrente = System.currentTimeMillis();
        List<Thread> hilos = new ArrayList<>();
        for (String archivo : args) {
            Thread hilo = new Thread(() -> contarCaracteresConcurrente(archivo));
            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Error al esperar un hilo: " + e.getMessage());
            }
        }
        long t_finConcurrente = System.currentTimeMillis();
        System.out.println("Tiempo de ejecución concurrente: " + (t_finConcurrente - t_comienzoConcurrente) + " ms");
    }

    public static void contarCaracteresSecuencial(String archivo) {
        try {
            String contenido = Files.readString(Paths.get(archivo));
            System.out.println("Archivo: " + archivo + " - Caracteres: " + contenido.length());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo " + archivo + ": " + e.getMessage());
        }
    }

    public static void contarCaracteresConcurrente(String archivo) {
        try {
            String contenido = Files.readString(Paths.get(archivo));
            System.out.println("Archivo (hilo): " + archivo + " - Caracteres: " + contenido.length());
        } catch (IOException e) {
            System.out.println("Error al leer el archivo (hilo) " + archivo + ": " + e.getMessage());
        }
    }
}