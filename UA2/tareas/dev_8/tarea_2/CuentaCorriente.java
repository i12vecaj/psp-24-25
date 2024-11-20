import java.util.Random;

public class CuentaCorriente {
    private double saldo;

    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void ingresar(double cantidad) {
        //public void ingresar(double cantidad) {
        System.out.println("*************************************************************");
        dormir();
        double saldoInicial = saldo;
        saldo += cantidad;
        double saldoFinal = saldo;
        System.out.println("Saldo antes del ingreso: " + saldoInicial);
        System.out.println("Saldo después del ingreso: " + saldoFinal);
    }

    private void dormir() {
        Random rango = new Random();
        int tiempo = rango.nextInt(1751) + 250; //min 250 max 2500
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
