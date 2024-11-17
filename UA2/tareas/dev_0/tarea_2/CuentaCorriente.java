import java.util.Random;

public class CuentaCorriente {
    private double saldo;

    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized double getSaldo() {
        sleepRandom();
        return saldo;
    }

    public synchronized void setSaldo(double saldo) {
        sleepRandom();
        this.saldo = saldo;
    }

    public synchronized void añadirSaldo(double cantidad, String nombreHilo) {
        System.out.println(nombreHilo + " va a añadir: " + cantidad);
        System.out.println("Saldo antes del ingreso: " + saldo);
        sleepRandom();
        saldo += cantidad;
        System.out.println("Saldo después del ingreso: " + saldo);
    }

    private void sleepRandom() {
        try {
            Random random = new Random();
            int tiempoEspera = 250 + random.nextInt(1751);
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Error en sleepRandom: " + e.getMessage());
        }
    }
}
