public class Main {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);
        System.out.println("Saldo inicial: " + cuenta.getSaldo());

        Thread hilo1 = new HiloIngreso(cuenta, 500, "Hilo 1");
        Thread hilo2 = new HiloIngreso(cuenta, 700, "Hilo 2");
        Thread hilo3 = new HiloIngreso(cuenta, 300, "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.err.println("Error esperando a los hilos: " + e.getMessage());
        }

        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}
