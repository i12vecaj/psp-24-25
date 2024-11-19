import java.util.Random;

public class CuentaCorriente {
    private double saldoCuentaCorriente;

    //constructor
    public CuentaCorriente(double saldoInicial) {
        this.saldoCuentaCorriente = saldoInicial;
    }

    //getter
    public double getSaldo() {
        try {
            Thread.sleep(new Random().nextInt(1750) + 250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return saldoCuentaCorriente;
    }

    //setter del saldo
    public void setSaldo(double saldoCuentaCorriente) {
        try {
            Thread.sleep(new Random().nextInt(1751) + 250);  // Sleep entre 250ms y 2000ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saldoCuentaCorriente = saldoCuentaCorriente;
    }

    //  sincronizado para añadir saldo
    public synchronized void agregarSaldo(double cantidad, String nombre) {
        try {
            Thread.sleep(new Random().nextInt(1751) + 250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(nombre + " va a ingresar " + cantidad + "€");
        System.out.println("Saldo previo: " + saldoCuentaCorriente);
        saldoCuentaCorriente += cantidad;
        System.out.println("Saldo actual: " + saldoCuentaCorriente);
    }
}
