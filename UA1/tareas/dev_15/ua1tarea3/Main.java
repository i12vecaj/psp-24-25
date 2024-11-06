public class Main {
    public static void main(String[] args) {
        // Creamos un objeto de la clase Ejecutar y le pasamos argumentos
        Ejecutar ejecutar = new Ejecutar(args);

        // Creamos un nuevo hilo y decimos que haga la tarea 'ejecutar'
        Thread hilo = new Thread(ejecutar);

        // Empezamos a ejecutar el hilo que acabamos de crear
        hilo.start();

        try {
            // Esperamos a que este hilo termine de ejecutarse
            hilo.join();
        } catch (InterruptedException e) {
            // Si algo interrumpe el hilo mostramos un error en la consola
            e.printStackTrace();
        }
    }
}