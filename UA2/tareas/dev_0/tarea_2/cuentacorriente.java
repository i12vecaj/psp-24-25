import java.util.Random;
import java.util.Scanner;

public class cuentacorriente{

    private double saldo;


    public cuentacorriente(double saldo) {
        this.saldo = 0;
    }



    public double getSaldo() {
        dormir();
        return saldo;
    }


    public void setSaldo(double saldo) {
        dormir();
        this.saldo = saldo;
    }


    private void dormir() {
        Random random = new Random();
        int tiempoDeEspera = 250 + random.nextInt(2000);
        try {
            Thread.sleep(tiempoDeEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public synchronized void realizarIngreso(double cantidad, String nombrePersona) {

        System.out.println(nombrePersona + " está realizando un ingreso.");
        System.out.println("Saldo previo: " + saldo);


        saldo += cantidad;


        System.out.println("Saldo final después del ingreso de " + cantidad + ": " + saldo);
    }

    public static void main(String[] args) {

        cuentacorriente cuenta = new cuentacorriente(0);
        Scanner escaner = new Scanner(System.in);

        System.out.println("Ingrese su nombre :");
        String nombre = escaner.nextLine();
        System.out.println("Ingrese el dinero a ingresar:");
        double cantidad = escaner.nextDouble();

        Thread h1 = new Thread(() -> cuenta.realizarIngreso(cantidad,nombre ));

        h1.start();
    }
}
