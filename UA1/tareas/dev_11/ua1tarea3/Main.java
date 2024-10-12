
// Main - Álvaro Fernández Amaro - Tarea 3
public class Main {


    public static void main(String[] args) {
        //Instancio nuevo objeto del hilo con los argumentos como parámetro
        ThreadPrograma programa = new ThreadPrograma(args);
        Thread hilo = new Thread(programa);
        //Inicializo el programa realizado en el hilo.
        hilo.start();

        //Hago manejo de errores para esperar que el hilo finalice correctamente sin ser interrumpido.
        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido.");
        }

        //Obtengo los datos del exitCode del hilo para realizar el System.exit() --> (FR2)
        int exitCode = programa.exitCode;
        if (exitCode != -1) {
            System.exit(exitCode);
        } else {
            System.exit(0);
        }
    }
}
