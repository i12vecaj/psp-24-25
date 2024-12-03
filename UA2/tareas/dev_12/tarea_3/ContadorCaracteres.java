package tarea_3;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class ContadorCaracteres {
    public static void main(String[] args) {
        // Verifica que se hayan pasado argumentos
        if (args.length == 0) {
            System.out.println("Error: Debes proporcionar una lista de archivos como argumentos.");
            return;
        }

        //Lista de rutas de archivos
        List<String> filePaths = Arrays.asList(args);

        //Ejecución secuencial
        System.out.println("\n--- Ejecución Secuencial ---");
        long t_comienzo = System.currentTimeMillis();
        for (String filePath : filePaths) {
            countCharactersSequential(filePath);
        }
        long t_fin = System.currentTimeMillis();
        long t_total = t_fin - t_comienzo;
        System.out.println("Tiempo total en ejecución secuencial: " + t_total + " ms");

        //Ejecución concurrente
        System.out.println("\n--- Ejecución Concurrente ---");
        t_comienzo = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(filePaths.size());
        for (String filePath : filePaths) {
            executor.execute(() -> countCharactersConcurrent(filePath));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("Error: Interrupción durante la ejecución concurrente.");
        }
        t_fin = System.currentTimeMillis();
        t_total = t_fin - t_comienzo;
        System.out.println("Tiempo total en ejecución concurrente: " + t_total + " ms");
    }

    //De forma secuencial
    public static void countCharactersSequential(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            System.out.println("Archivo: " + filePath + " - Número de caracteres: " + content.length());
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo " + filePath + ": " + e.getMessage());
        }
    }

    //De forma concurrente
    public static void countCharactersConcurrent(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            System.out.println("Archivo: " + filePath + " - Número de caracteres: " + content.length());
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo " + filePath + ": " + e.getMessage());
        }
    }
}