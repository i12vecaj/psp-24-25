import java.io.*;
import java.nio.file.*;

public class ua2tarea3 {

    public static void main(String[] args) {
        // Lista de archivos a procesar
        String[] archivos = {"ElQuijote1.txt", "ElQuijote2.txt", "ElQuijote3.txt"};

        // Crear e iniciar un hilo para procesar cada archivo
        for (String archivo : archivos) {
            // Crear un nuevo hilo que ejecute la tarea definida en procesadorArchivos
            Thread hilos = new Thread(new procesadorArchivos(archivo));
            hilos.start(); // Iniciar la ejecución del hilo
        }
    }
}
// Clase que implementa Runnable
class procesadorArchivos implements Runnable {
    private final String ruta; // Ruta del archivo a procesar

    // Constructor
    public procesadorArchivos(String camino) {
        this.ruta = camino;
    }

    @Override
    public void run() {
        try {
            // Leer el contenido completo del archivo desde su ruta
            String contenido = Files.readString(Paths.get(ruta));
            // Contar el número de caracteres en el archivo
            int contadotCaracter = contenido.length();
            System.out.println("El archivo \"" + ruta + "\" tiene " + contadotCaracter + " caracteres.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo \"" + ruta + "\": " + e.getMessage());
        }
    }
}

/*En la ejecución secuencial es más lento ya que los archivos se procesan uno tras otro
 y el tiempo total es la suma de los tiempos individuales. En la ejecución concurrente
 es más rápido ya que los archivos se procesan en paralelo y el tiempo total es el
 tiempo del archivo más grande. */