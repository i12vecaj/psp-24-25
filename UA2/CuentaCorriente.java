import java.util.Random;

public class CuentaCorriente {
    private double saldo; //representa el saldo de la cuenta

 
    public CuentaCorriente(double saldoInicial) {  
        this.saldo = saldoInicial;
    }


    public double getSaldo() {           //Inicia la cuenta con un saldo inicial proporcionado
        try {
            Thread.sleep(generarSleepAleatorio());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return saldo;
    }

    public void setSaldo(double nuevoSaldo) {        
        try {
            Thread.sleep(generarSleepAleatorio());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        this.saldo = nuevoSaldo;
    }


    public synchronized void ingresar(double cantidad, String quienRealizaElIngreso) {   //Incrementa el saldo de la cuenta en una cantidad específica
        System.out.println("Saldo previo: " + saldo);
        saldo += cantidad;
        System.out.println("Ingreso de: " + cantidad + " hecho por: " + quienRealizaElIngreso);
        System.out.println("Saldo final: " + saldo);
    }

    private int generarSleepAleatorio() {
        Random random = new Random();
        return 250 + random.nextInt(1751); 
    }
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(-400.0);  //Empiezan los hilos y con la cantidad de las cuentas

        HiloIngreso hilo1 = new HiloIngreso(cuenta, 500.0, "Hilo1");
        HiloIngreso hilo2 = new HiloIngreso(cuenta, 300.0, "Hilo2");

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());
    }
}


class HiloIngreso extends Thread {
    private CuentaCorriente cuenta;
    private double cantidad;
    private String nombre;

    public HiloIngreso(CuentaCorriente cuenta, double cantidad, String nombre) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        cuenta.ingresar(cantidad, nombre);
    }
}

