public class Main {
    public static void main(String[] args) {
        Ejecutar ejecutar = new Ejecutar(args);
        Thread hilo = new Thread(ejecutar);
        //Iniciamos el hilo
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha inerrumpido.");
        }
    }
}
