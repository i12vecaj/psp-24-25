package tarea2;

public class Main {
    public static void main(String[] args) {

        //Creamos la cuenta corriente 
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);
        
        System.out.println("Saldo inicial: " + cuenta.getSaldo());
        
        //Generamos el ingreso pasando como parametros la cuenta, cantidad a ingresar y nombre
        Thread ingreso1 = new GenerarIngreso(cuenta, 200.0, "Antonio");
        Thread ingreso2 = new GenerarIngreso(cuenta, 2.5, "Ruben");
        Thread ingreso3 = new GenerarIngreso(cuenta, 500.0, "Juan");

        //Iniciamos los hilos
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
        
        //Mostrar el saldo final
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}

