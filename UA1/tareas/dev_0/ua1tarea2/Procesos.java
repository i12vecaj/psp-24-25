import java.io.IOException;

public class Procesos {
    public static void main(String[] args) {
        try {
            // Usamos ProcessBuilder para ejecutar programa Main
            ProcessBuilder processBuilder = new ProcessBuilder("java", "Main");

            // InheritIO permite la salida del Main
            processBuilder.inheritIO();

            // Iniciamos el proceso que ejecuta Main
            Process process = processBuilder.start();

            // Esperamos a que el programa Main se ejecute
            int exitCode = process.waitFor();

            // Mostramos el código de salida de Main para saber si todo esta correcto
            System.out.println("El programa Main terminó con éxito, código de salida: " + exitCode);
        } catch (IOException | InterruptedException e) {
            // Si hubo un problema al ejecutar Main, mostramos un error
            System.err.println("Error al ejecutar el programa: " + e.getMessage());
        }
    }
}