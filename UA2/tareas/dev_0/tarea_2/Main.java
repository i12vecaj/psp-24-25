public class Main {
    public static void main(String[] args) {
        // Saldo inicial
        double saldoInicial = 1000.0;
        CuentaCorriente cuenta = new CuentaCorriente(saldoInicial);
        System.out.println("Saldo inicial: " + cuenta.getSaldo());
        System.out.println("----------------------------------------");

        // Crear y lanzar hilos
        HiloCuentaCorriente hilo1 = new HiloCuentaCorriente(cuenta, 500, "Hilo1");
        HiloCuentaCorriente hilo2 = new HiloCuentaCorriente(cuenta, 300, "Hilo2");
        HiloCuentaCorriente hilo3 = new HiloCuentaCorriente(cuenta, 200, "Hilo3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperar a que los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error al esperar los hilos: " + e.getMessage());
        }

        // Mostrar saldo final
        System.out.println("Saldo final (con synchronized): " + cuenta.getSaldo());
        System.out.println("----------------------------------------");

        // Repetir sin synchronized
        System.out.println("Ejecutando sin synchronized...");
        CuentaCorriente cuentaNoSync = new CuentaCorriente(saldoInicial);
        System.out.println("Saldo inicial: " + cuentaNoSync.getSaldo());

        HiloCuentaCorriente hilo4 = new HiloCuentaCorriente(cuentaNoSync, 500, "Hilo4");
        HiloCuentaCorriente hilo5 = new HiloCuentaCorriente(cuentaNoSync, 300, "Hilo5");
        HiloCuentaCorriente hilo6 = new HiloCuentaCorriente(cuentaNoSync, 200, "Hilo6");

        hilo4.start();
        hilo5.start();
        hilo6.start();

        try {
            hilo4.join();
            hilo5.join();
            hilo6.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error al esperar los hilos: " + e.getMessage());
        }

        System.out.println("Saldo final (sin synchronized): " + cuentaNoSync.getSaldo());
    }
}
