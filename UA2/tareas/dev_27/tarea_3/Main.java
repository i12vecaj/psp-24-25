import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    // Método para contar caracteres en un archivo
    private static long countCharacters(String fileName) {
        long count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = reader.read()) != -1) count++;
        } catch (IOException e) {
            System.err.println("Error en archivo " + fileName + ": " + e.getMessage());
        }
        return count;
    }

    // Proceso secuencial
    private static void sequentialProcessing(String[] files) {
        for (String file : files)
            System.out.println(file + ": " + countCharacters(file) + " caracteres.");
    }

    // Proceso concurrente
    private static void concurrentProcessing(String[] files) {
        Thread[] threads = new Thread[files.length];
        for (int i = 0; i < files.length; i++) {
            final String file = files[i];
            threads[i] = new Thread(() -> System.out.println(file + ": " + countCharacters(file) + " caracteres."));
            threads[i].start();
        }
        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException e) { }
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java Main <archivo1> <archivo2> ...");
            return;
        }

        long start, end;

        // Proceso secuencial
        start = System.currentTimeMillis();
        sequentialProcessing(args);
        end = System.currentTimeMillis();
        System.out.println("Tiempo secuencial: " + (end - start) + " ms\n");

        // Proceso concurrente
        start = System.currentTimeMillis();
        concurrentProcessing(args);
        end = System.currentTimeMillis();
        System.out.println("Tiempo concurrente: " + (end - start) + " ms\n");
    }
}

