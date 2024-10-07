import java.io.IOException;

//La clase "LanzarProcesos" ejecuta el programa llamado "MainProcesos".
public class LanzarProcesos {
    public static void main(String[] args) {
        try {
            //Dentro del ProcessBuilder ejecutamos el codigo "MainProcesos", para que defina el comando a ejecutar y sus argumentos.
            //"java" es el que lanza el programa y "MainProcesos" es el programa que queremos que se lance.
            ProcessBuilder processBuilder = new ProcessBuilder("java", "MainProcesos");
            processBuilder.inheritIO();
            Process process = processBuilder.start();

            //Ahora esperamos a que el subproceso termine de ejecutarse.
            //El método waitFor() detiene el programa pricipal hasta que el subproceso termine (para no saturar el programa).
            int exitCode = process.waitFor();

            //Muestro el código de salida del subproceso para indicar si terminó correctamente o si hubo algún error.
            System.out.println("El programa MainProcesos terminó con exito :) " + exitCode);
        } catch (IOException | InterruptedException e) {

            //Si hay algun error al intentar ejecutar el subproceso, mostramos un mensaje  de error.
            System.err.println("Hay un error el en programa :( " + e.getMessage());
        }
    }
}