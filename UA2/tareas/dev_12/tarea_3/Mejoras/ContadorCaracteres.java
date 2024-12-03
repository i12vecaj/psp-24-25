package tarea_3.Mejoras;

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

        // Lista de rutas de archivos
        List<String> filePaths = Arrays.asList(args);

        // Ejecución secuencial
        System.out.println("\n--- Ejecución Secuencial ---");
        long t_comienzo = System.currentTimeMillis();
        for (String filePath : filePaths) {
            countCharactersSequential(filePath);
        }
        long t_fin = System.currentTimeMillis();
        long t_total = t_fin - t_comienzo;
        System.out.println("Tiempo total en ejecución secuencial: " + t_total + " ms");

        // Ejecución concurrente
        System.out.println("\n--- Ejecución Concurrente ---");
        t_comienzo = System.currentTimeMillis();

        // Configuración dinámica del tamaño del pool de hilos
        int numThreads = Math.min(filePaths.size(), Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        // Lista para recoger futuros
        List<Future<?>> futures = new ArrayList<>();
        for (String filePath : filePaths) {
            futures.add(executor.submit(() -> {
                countCharactersConcurrent(filePath);
                return null;
            }));
        }

        // Asegurar la captura de errores en las tareas concurrentes
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (Exception e) {
                System.out.println("Error en una tarea concurrente: " + e.getMessage());
            }
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

    // De forma secuencial
    public static void countCharactersSequential(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.isReadable(path)) {
                System.out.println("Error: El archivo " + filePath + " no es legible o no existe.");
                return;
            }

            String content = new String(Files.readAllBytes(path));
            System.out.println("Archivo: " + filePath + " - Número de caracteres: " + content.length());
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo " + filePath + ": " + e.getMessage());
        }
    }

    // De forma concurrente
    public static void countCharactersConcurrent(String filePath) {
        try {
            Path path = Paths.get(filePath);
            if (!Files.isReadable(path)) {
                System.out.println("Error: El archivo " + filePath + " no es legible o no existe.");
                return;
            }

            String content = new String(Files.readAllBytes(path));
            System.out.println("Archivo: " + filePath + " - Número de caracteres: " + content.length());
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo " + filePath + ": " + e.getMessage());
        }
    }
}
