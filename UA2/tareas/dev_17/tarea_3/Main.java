import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> nombresArchivos = new ArrayList<String>();//Declaramos un arraylist
        Scanner scanner = new Scanner(System.in); //El scanner para poder leer el nombre del archivo
        String nombreArchivo; //Declaramos la variable nombreArchivo

        do { //Creamos un do while para repetir hasta que el usuario introduzca *
            System.out.println("Introduzca el nombre del fichero del archivo, para terminar introduzca (*):");
            nombreArchivo = scanner.nextLine();//

            if (!nombreArchivo.equals("*")) { //Si es diferente a * lo añadirimos a la lista de archivos
                nombresArchivos.add(nombreArchivo);
            }
        }while (!nombreArchivo.equals("*"));
        scanner.close();


        List<Thread> hilos = new ArrayList<>();// Lista para almacenar los hilos

        // Creamos un hilo por cada archivo y lo iniciamos
        for (String archivo : nombresArchivos) {
            Hilo hilo = new Hilo(archivo + ".txt"); // Añadimos ".txt" si no lo incluye
            hilos.add(hilo); // Añadimos el hilo a la lista
            hilo.start(); // Iniciamos el hilo
        }

        // Esperamos a que terminen todos los hilos
        for (Thread hilo : hilos) {
            try {
                hilo.join(); // Bloquea el hilo principal hasta que termine cada hilo
            } catch (InterruptedException e) {
                System.err.println("Error al esperar el hilo: " + hilo.getName());
            }
        }

        System.out.println("Todos los archivos han sido procesados.");
    }
}
