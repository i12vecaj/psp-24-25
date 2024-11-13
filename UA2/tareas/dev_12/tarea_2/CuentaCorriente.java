package tarea2;

import java.util.Random;

public class CuentaCorriente {
    private double saldo;

    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized double getSaldo() {
        simularRetraso();
        return saldo;
    }

    public synchronized void setSaldo(double nuevoSaldo) {
        simularRetraso();
        this.saldo = nuevoSaldo;
    }

    public synchronized void ingresar(double cantidad, String nombrePersona) {
        System.out.println(nombrePersona + " esta ingresando una cantidad de: " + cantidad);
        simularRetraso();
        System.out.println("Saldo anterior: " + saldo);
        saldo += cantidad;
        System.out.println("Saldo después del ingreso: " + saldo);
    }

    private void simularRetraso() {
        try {
            int tiempoEspera = new Random().nextInt(1751) + 250;
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("El hilo fue interrumpido.");
        }
    }
}

