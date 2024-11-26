import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ua2tarea3fr2 {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("tiene que tener un archivo de documento de texto");
            return;
        }

        long tComienzo = System.currentTimeMillis();

        Thread[] hilos = new Thread[args.length];
        for (int i = 0; i < args.length; i++) {
            String fileName = args[i];
            hilos[i] = new Thread(() -> contarCaracteresConcurrente(fileName));
            hilos[i].start();
        }


        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("hay un error al finalizar el hilo " + e.getMessage());
            }
        }

        long tFin = System.currentTimeMillis();
        long tTotal = tFin - tComienzo;
        System.out.println("tiempo ejecucion " + tTotal + " ms.");
    }

    private static void contarCaracteresConcurrente(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            int c;
            while ((c = reader.read()) != -1) {
                count++;
            }
            System.out.println("Archivo '" + fileName + "': " + count + " caracteres.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo '" + fileName + "': " + e.getMessage());
        }
    }
}

