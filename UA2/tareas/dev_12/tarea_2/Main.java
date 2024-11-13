package tarea2;

public class Main {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);
        
        System.out.println("Saldo inicial: " + cuenta.getSaldo());
        
        Thread ingreso1 = new GenerarIngreso(cuenta, 200.0, "Antonio");
        Thread ingreso2 = new GenerarIngreso(cuenta, 2.5, "Ruben");
        Thread ingreso3 = new GenerarIngreso(cuenta, 500.0, "Juan");

        ingreso1.start();
        ingreso2.start();
        ingreso3.start();

        try {
            ingreso1.join();
            ingreso2.join();
            ingreso3.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo principal fue interrumpido.");
        }
        
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}

