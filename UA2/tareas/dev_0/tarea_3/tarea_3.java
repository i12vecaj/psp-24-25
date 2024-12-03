import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class tarea_3 { //javac tarea_3.java
    public static void main(String[] args) throws InterruptedException {
        if (args.length < 1) {
            System.out.println("Por favor, proporciona al menos un archivo de texto.");
            return;
        }

        long t_comienzo = System.currentTimeMillis();
        conteoSecuencial(args);
        long t_fin = System.currentTimeMillis();
        long t_total_secuencial = t_fin - t_comienzo;
        System.out.println("Tiempo de ejecución secuencial: " + t_total_secuencial + " ms");

        System.out.println("----------------------------");

        t_comienzo = System.currentTimeMillis();
        conteoConConcurrencia(args);
        t_fin = System.currentTimeMillis();
        long t_total_concurrente = t_fin - t_comienzo;
        System.out.println("Tiempo de ejecución concurrente: " + t_total_concurrente + " ms");
    }

    public static void conteoSecuencial(String[] archivos) {
        long totalCaracteres = 0;
        for (String archivo : archivos) {
            totalCaracteres += contarCaracteresArchivo(archivo);
        }
        System.out.println("Total de caracteres (Secuencial): " + totalCaracteres);
    }

    public static void conteoConConcurrencia(String[] archivos) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(archivos.length);
        List<Long> resultados = new ArrayList<>();

        for (String archivo : archivos) {
            executor.submit(() -> {
                long caracteresArchivo = contarCaracteresArchivo(archivo);
                synchronized (resultados) {
                    resultados.add(caracteresArchivo);
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long totalCaracteres = resultados.stream().mapToLong(Long::longValue).sum();
        System.out.println("Total de caracteres (Concurrente): " + totalCaracteres);
    }

    private static long contarCaracteresArchivo(String nombreArchivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            long caracteresArchivo = reader.lines()
                    .mapToLong(linea -> linea.length())
                    .sum();
            return caracteresArchivo;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo " + nombreArchivo);
            return 0;
        }
    }
}