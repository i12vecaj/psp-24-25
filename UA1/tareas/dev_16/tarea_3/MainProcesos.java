public class MainProcesos {
    public static void main(String[] args) {
        LeerProcesos lector = new LeerProcesos(); // Sin argumentos
        Thread hilo = new Thread(lector); // Crear un hilo con el lector

        hilo.start(); // Iniciar el hilo

        try {
            hilo.join(); // Esperar a que el hilo termine
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido.");
        }
    }
}