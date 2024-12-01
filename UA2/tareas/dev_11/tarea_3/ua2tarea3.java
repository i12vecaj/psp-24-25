import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ua2tarea3 {

    // Clase hilo encargado de procesar un archivo
    static class ArchivoProcesador extends Thread {
        private String nombreArchivo;
        public int caracteres;

        // Constructor que inicializa el nombre del archivo
        public ArchivoProcesador(String nombreArchivo) {
            this.nombreArchivo = nombreArchivo;
            setName(nombreArchivo);
        }

        @Override
        public void run() {
            try (FileReader lector = new FileReader(nombreArchivo + ".txt")) {
                int caracter;
                // Lee cada carácter del archivo hasta el final
                while ((caracter = lector.read()) != -1) {
                    caracteres++;
                }
                System.out.println("Archivo: " + nombreArchivo + " - Total caracteres: " + caracteres);
            } catch (IOException e) {
                System.err.println("Error al procesar el archivo " + nombreArchivo + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // Solicita el nombre del archivo
        String nombreArchivo = obtenerNombreArchivo();

        // Procesa el archivo de manera secuencial
        System.out.println("\nEjecución Secuencial: ");
        long tiempoInicioSecuencial = System.currentTimeMillis();
        procesarArchivoSecuencial(nombreArchivo + ".txt");
        long tiempoFinSecuencial = System.currentTimeMillis();
        System.out.println("Tiempo total (secuencial): " + (tiempoFinSecuencial - tiempoInicioSecuencial) + " ms");

        // Procesa el archivo mediante un hilo de manera concurrente
        System.out.println("\nEjecución Concurrente: ");
        long tiempoInicioConcurrente = System.currentTimeMillis();
        ArchivoProcesador hilo = new ArchivoProcesador(nombreArchivo);
        hilo.start();

        // Espera a que el hilo termine
        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.err.println("Error esperando al hilo: " + e.getMessage());
        }
        long tiempoFinConcurrente = System.currentTimeMillis();
        System.out.println("Tiempo total (concurrente): " + (tiempoFinConcurrente - tiempoInicioConcurrente) + " ms");
        System.exit(0);
    }

    // Solicito el nombre de un archivo al usuario
    private static String obtenerNombreArchivo() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduce el nombre del archivo (sin la extensión):");
        return entrada.nextLine();
    }

    // Procesa un archivo de manera secuencial y cuenta sus caracteres
    public static void procesarArchivoSecuencial(String nombreArchivo) {
        try (FileReader lector = new FileReader(nombreArchivo)) {
            int totalCaracteres = 0;
            int caracter;
            // Lee cada carácter del archivo hasta el final
            while ((caracter = lector.read()) != -1) {
                totalCaracteres++;
            }
            System.out.println("Archivo: " + nombreArchivo + ".txt" + " - Total caracteres: " + totalCaracteres);
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
