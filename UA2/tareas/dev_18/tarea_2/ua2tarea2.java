import java.util.Scanner;
import java.util.Random;

class CuentaCorriente {
    private double saldo;

    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized double getSaldo() {
        randomSleep();
        return saldo;
    }

    public synchronized void setSaldo(double saldo) {
        randomSleep();
        this.saldo = saldo;
    }

    public synchronized void agregarSaldo(double cantidad, String quien) {
        randomSleep();
        double saldoPrevio = saldo;//Señalizamos que el saldo previo es el saldo incial y a raiz que le vamos metiendo cantidad va sumando al saldo
        saldo += cantidad;
        System.out.printf("%s añade %.2f a la cuenta. Saldo previo: %.2f, Saldo final: %.2f%n", quien, cantidad, saldoPrevio, saldo);
    }

    private void randomSleep() {
        try {
            Random random = new Random();
            int tiempo = 250 + random.nextInt(1751); 
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            System.err.println("Hilo interrumpido: " + e.getMessage());
        }
    }
}

class HiloIngreso extends Thread {
    private final CuentaCorriente cuenta;
    private final double cantidad;

    public HiloIngreso(CuentaCorriente cuenta, double cantidad, String nombre) {
        super(nombre);
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }
    @Override
    public void run() {
        cuenta.agregarSaldo(cantidad, getName());
    }
}

public class ua2tarea2 {
    public static void ua2tarea2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);// ya empieza con un saldo inicial
        System.out.printf("Saldo inicial de la cuenta: %.2f%n", cuenta.getSaldo());
        System.out.println("Introduce las cantidades para los ingresos:"); // Solicita al usuario el dinero a ingresar para cada usuario(hilo)
        System.out.print("Cantidad para Juan: ");
        double cantidad1 = scanner.nextDouble();
        System.out.print("Cantidad para Marcos: ");
        double cantidad2 = scanner.nextDouble();
        System.out.print("Cantidad para Ana: ");
        double cantidad3 = scanner.nextDouble();
        
        Thread hilo1 = new HiloIngreso(cuenta, cantidad1, "Juan");
        Thread hilo2 = new HiloIngreso(cuenta, cantidad2, "Marcos");
        Thread hilo3 = new HiloIngreso(cuenta, cantidad3, "Ana");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.err.println("Error al introducir los hilos: " + e.getMessage());
        }   
        System.out.printf("Saldo final de la cuenta: %.2f€%n", cuenta.getSaldo());// Muestra el saldo despues de meter todas las cantidades

        scanner.close();
    }
}
