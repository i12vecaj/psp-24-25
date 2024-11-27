import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String archivoUno = "src/archivo1.txt";
        String archivoDos = "src/archivo2.txt";
        String archivoTres = "src/archivo3.txt";

        System.out.println("\n--- Ejecución Secuencial ---");
        long inicioSecuencial = System.currentTimeMillis();
        contarCaracteresSecuencial(archivoUno);
        contarCaracteresSecuencial(archivoDos);
        contarCaracteresSecuencial(archivoTres);
        long finSecuencial = System.currentTimeMillis();
        System.out.println("Tiempo de la primera parte: " + (finSecuencial - inicioSecuencial) + " milisegundos");

        System.out.println("\n--- Ejecución Concurrente ---");
        long inicioConcurrente = System.currentTimeMillis();


        List<Ficheros> hilos = new ArrayList<>();
        Ficheros hilo1 = new Ficheros(archivoUno);
        Ficheros hilo2 = new Ficheros(archivoDos);
        Ficheros hilo3 = new Ficheros(archivoTres);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilos.add(hilo1);
        hilos.add(hilo2);
        hilos.add(hilo3);

        for (Ficheros hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.err.println("Error esperando al hilo: " + e.getMessage());
            }
        }

        long finConcurrente = System.currentTimeMillis();
        System.out.println("Tiempo en la segunda parte: " + (finConcurrente - inicioConcurrente) + " milisegundos");

        System.out.println("Todos los hilos han terminado.");
    }

    public static void contarCaracteresSecuencial(String nombreArchivo) {
        System.out.println("Procesando archivo secuencialmente: " + nombreArchivo);
        try (FileReader fr = new FileReader(nombreArchivo)) {
            int contadorCaracteres = 0;
            int caracter;
            while ((caracter = fr.read()) != -1) {
                contadorCaracteres++;
            }
            System.out.println("Archivo: " + nombreArchivo + " - Total caracteres: " + contadorCaracteres);
        } catch (IOException e) {
            System.err.println("Error procesando archivo " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
