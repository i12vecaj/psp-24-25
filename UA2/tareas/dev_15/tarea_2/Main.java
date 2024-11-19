import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Creamos una cuenta con saldo inicial
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);
        System.out.println("Saldo inicial: " + cuenta.getSaldo());

        // Creamos tres hilos que usarán la misma cuenta
        Thread hilo1 = new HiloIngreso(cuenta, 500.0, "Hilo-1");
        Thread hilo2 = new HiloIngreso(cuenta, 300.0, "Hilo-2");
        Thread hilo3 = new HiloIngreso(cuenta, 200.0, "Hilo-3");

        // Arrancamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperamos a que terminen
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.out.println("Error esperando hilos.");
        }

        // Mostramos el saldo final
        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}

// Representa una cuenta con saldo
class CuentaCorriente {
    private double saldo;

    // Ponemos el saldo inicial
    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // Obtenemos el saldo (con pausa para simular trabajo)
    public synchronized double getSaldo() {
        dormirUnPoco();
        return saldo;
    }

    // Agregamos saldo a la cuenta
    public synchronized void agregarSaldo(double cantidad, String nombreHilo) {
        System.out.println(nombreHilo + " - Saldo antes: " + saldo);
        saldo += cantidad;
        System.out.println(nombreHilo + " - Saldo después: " + saldo);
    }

    // Simulamos tiempo de espera
    private void dormirUnPoco() {
        try {
            Thread.sleep(new Random().nextInt(1751) + 250);
        } catch (InterruptedException e) {
            System.out.println("Error al dormir.");
        }
    }
}

// Hilo que agrega dinero
class HiloIngreso extends Thread {
    private final CuentaCorriente cuenta;
    private final double cantidad;

    // Configuramos qué cuenta y cuánto dinero usará este hilo
    public HiloIngreso(CuentaCorriente cuenta, double cantidad, String nombre) {
        super(nombre);
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    // Al correr el hilo, agregamos saldo
    @Override
    public void run() {
        cuenta.agregarSaldo(cantidad, getName());
    }
}
