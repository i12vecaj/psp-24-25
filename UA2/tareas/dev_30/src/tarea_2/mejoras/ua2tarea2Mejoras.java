package tarea_2.mejoras;

public class ua2tarea2Mejoras {
    public static void main(String[] args) throws InterruptedException {
        CuentaCorrienteMejoras cuenta = new CuentaCorrienteMejoras(2000);

        // Crear los hilos con Runnable
        Thread hilo1 = new Thread(new AddSaldoCuentaMejoras("pepe Moriles", 500));
        Thread hilo2 = new Thread(new AddSaldoCuentaMejoras("Jose Pablo", 600));
        Thread hilo3 = new Thread(new AddSaldoCuentaMejoras("Manolo Torres", 700));

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que terminen todos los hilos
        hilo1.join();
        hilo2.join();
        hilo3.join();
    }
}
