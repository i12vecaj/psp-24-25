import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ficheros> hilos = new ArrayList<>();
        List<String> list = List.of("archivo1", "archivo2", "archivo3");

        System.out.println("\n--- Ejecución Secuencial ---");
        long startSequential = System.currentTimeMillis();
        for (String name : list) {
            countCharactersSequential("src/" + name + ".txt");
        }
        long endSequential = System.currentTimeMillis();
        System.out.println("Tiempo de la primera parte: " + (endSequential - startSequential) + " milisegundos");

        System.out.println("\n--- Ejecución Concurrente ---");
        long startConcurrent = System.currentTimeMillis();
        for (String nombre : list) {
            Ficheros hilo = new Ficheros("src/" + nombre + ".txt");
            hilo.start();
            hilos.add(hilo);
        }

        for (Ficheros hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error esperando al hilo: " + e.getMessage());
            }
        }
        long endConcurrent = System.currentTimeMillis();
        System.out.println("Tiempo en la segunda parte: " + (endConcurrent - startConcurrent) + " milisegundos");

        System.out.println("Todos los hilos han terminado.");
    }

    public static void countCharactersSequential(String fileName) {
        try (FileReader fr = new FileReader(fileName)) {
            int charCount = 0;
            int c;
            while ((c = fr.read()) != -1) {
                charCount++;
            }
            System.out.println("Archivo de texto: " + fileName + " - Caracteres: " + charCount);
        } catch (IOException e) {
            System.err.println("Error procesando archivo " + fileName + ": " + e.getMessage());
        }
    }
}