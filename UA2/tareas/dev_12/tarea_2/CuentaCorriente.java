package tarea_2;

import java.util.Random;

public class CuentaCorriente {
    private double saldo;

    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    //Sincronizamos el metodo para que no se cruzen los hilos
    public synchronized double getSaldo() {
        simularRetraso();
        return saldo;
    }

    //Cambiamos el saldo y realizamos el Retraso
    public synchronized void setSaldo(double nuevoSaldo) {
        simularRetraso();
        this.saldo = nuevoSaldo;
    }

    //Sumamos la cantidad a ingresar al saldo de la cuenta
    public synchronized void ingresar(double cantidad, String nombrePersona) {
        System.out.println(nombrePersona + " esta ingresando una cantidad de: " + cantidad);
        System.out.println("Saldo anterior: " + saldo);
        saldo += cantidad;
        System.out.println("Saldo después del ingreso: " + saldo);
    }

    //Simulamos el retraso mediante un sleep y un numero aletorio
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

