import java.util.Random;
import java.util.Scanner;

public class cuentacorriente {

    private double saldo;

    public cuentacorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized double getSaldo() {
        dormir();
        return saldo;
    }

    public synchronized void setSaldo(double saldo) {
        dormir();
        this.saldo = saldo;
    }

    public synchronized void realizarIngreso(double cantidad, String nombrePersona) {
        System.out.println(nombrePersona + " está realizando un ingreso.");
        System.out.println("Saldo previo: " + saldo);
        saldo += cantidad;
        System.out.println("Saldo final después del ingreso de " + cantidad + ": " + saldo);
    }

    private void dormir() {
        Random random = new Random();
        int tiempoDeEspera = 250 + random.nextInt(1750);
        try {
            Thread.sleep(tiempoDeEspera);
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public static void main(String[] args) {


            cuentacorriente cuenta = new cuentacorriente(1000.0);

            Thread hilo1 = new Thread(() -> cuenta.realizarIngreso(500.0, "paco"));
            Thread hilo2 = new Thread(() -> cuenta.realizarIngreso(300.0, "matas"));

            hilo1.start();
            hilo2.start();

            try {
                hilo1.join();
                hilo2.join();
            } catch (InterruptedException e) {
                System.out.println("error con el hilo: " + e.getMessage());
            }

            System.out.println("este es el saldo final: " + cuenta.getSaldo());
        }
    }















