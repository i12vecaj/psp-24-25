import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Lista para almacenar los nombres de los archivos
        ArrayList<String> nombresArchivos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); // Para leer la entrada del usuario
        String nombreArchivo; // Variable para almacenar cada nombre de archivo

        // Pedimos al usuario que ingrese los nombres de los archivos
        do {
            System.out.println("Introduzca el nombre del fichero, para terminar introduzca (*):");
            nombreArchivo = scanner.nextLine(); // Leemos el nombre del archivo

            // Si no ha introducido *, lo agregamos a la lista de archivos
            if (!nombreArchivo.equals("*")) {
                nombresArchivos.add(nombreArchivo);
            }
        } while (!nombreArchivo.equals("*")); // El bucle se repite hasta que el usuario introduzca *

        scanner.close(); // Cerramos el scanner

        // Lista para almacenar los hilos que procesarán los archivos
        List<Thread> hilos = new ArrayList<>();

        // Creamos un hilo por cada archivo en la lista
        for (String archivo : nombresArchivos) {
            // Creamos un nuevo hilo que procesará el archivo
            Hilo hilo = new Hilo(archivo + ".txt"); // Aseguramos que el nombre del archivo tenga ".txt"
            hilos.add(hilo); // Añadimos el hilo a la lista
            hilo.start(); // Iniciamos el hilo
        }

        // Esperamos a que todos los hilos terminen antes de finalizar el programa
        for (Thread hilo : hilos) {
            try {
                hilo.join(); // Esperamos a que cada hilo termine su ejecución
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + hilo.getName());
            }
        }

        System.out.println("Todos los archivos han sido procesados."); // Mensaje final
    }

    // Clase Hilo interna que extiende Thread para contar caracteres de cada archivo
    static class Hilo extends Thread {
        private String archivo;

        // Constructor que recibe el nombre del archivo
        public Hilo(String archivo) {
            this.archivo = archivo;
        }

        @Override
        public void run() {
            // Llamamos al método para contar caracteres de este archivo
            contarCaracteresConcurrente(archivo);
        }

        // Método para contar caracteres de manera concurrente (para ser usado en hilos)
        private void contarCaracteresConcurrente(String archivo) {
            // Intentamos leer el archivo
            try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
                int contador = 0; // Inicializa el contador de caracteres
                int c;
                // Lee el archivo carácter por carácter
                while ((c = lector.read()) != -1) {
                    contador++; // Suma uno por cada carácter leído
                }
                // Muestra el número de caracteres leídos en el archivo
                System.out.println("Archivo: " + archivo + " - Caracteres: " + contador);
            } catch (IOException e) {
                // Si no puede leer el archivo, muestra un mensaje de error
                System.out.println("No se pudo leer el archivo " + archivo + ": " + e.getMessage());
            }
        }
    }
}
