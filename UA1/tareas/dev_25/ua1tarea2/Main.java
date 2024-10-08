public class Main {
    public static void main(String[] args) {
        // Crear una instancia del hilo que ejecuta el método `run()` de LeerCadena
        Thread hilo = new Thread(new Ejecutar());

        // Iniciar el hilo
        hilo.start();
        System.out.println("El programa ha terminado.");
    }
}
