public class Main {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(1000);
        System.out.println("Saldo inicial de la cuenta: " + cuenta.getSaldo());

        IngresoHilo hilo1 = new IngresoHilo(cuenta, 200, "Hilo1");
        IngresoHilo hilo2 = new IngresoHilo(cuenta, 300, "Hilo2");
        IngresoHilo hilo3 = new IngresoHilo(cuenta, 150, "Hilo3");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.err.println("Error al esperar a los hilos: " + e.getMessage());
        }

        System.out.println("Saldo final de la cuenta: " + cuenta.getSaldo());

        System.out.println("\nPrueba sin synchronized:");

        CuentaCorriente cuentaSinSync = new CuentaCorriente(1000);
        System.out.println("Saldo inicial (sin synchronized): " + cuentaSinSync.getSaldo());

        IngresoHilo hiloA = new IngresoHilo(cuentaSinSync, 200, "HiloA");
        IngresoHilo hiloB = new IngresoHilo(cuentaSinSync, 300, "HiloB");
        IngresoHilo hiloC = new IngresoHilo(cuentaSinSync, 150, "HiloC");

        hiloA.start();
        hiloB.start();
        hiloC.start();

        try {
            hiloA.join();
            hiloB.join();
            hiloC.join();
        } catch (InterruptedException e) {
            System.err.println("Error al esperar a los hilos: " + e.get Message());
        }

        System.out.println("Saldo final de la cuenta (sin synchronized): " + cuentaSinSync.getSaldo());
    }
}