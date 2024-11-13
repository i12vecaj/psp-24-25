import java.util.Random;

public class CuentaCorriente {
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

    public synchronized void ingresar(double cantidad, String nombreHilo) {
        System.out.println(nombreHilo + " - Saldo antes del ingreso: " + saldo);
        saldo += cantidad;
        System.out.println(nombreHilo + " - Saldo después del ingreso: " + saldo);
    }

    private void randomSleep() {
        try {
            int tiempoDormir = new Random().nextInt(1751) + 250;
            Thread.sleep(tiempoDormir);
        } catch (InterruptedException e) {
            System.err.println("Error en el sleep aleatorio: " + e.getMessage());
        }
    }
}