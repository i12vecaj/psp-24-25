package UA2.tareas.dev_0.tarea_2.ua2tarea2fr2.src;

import java.util.Random;
import java.util.Scanner;
public class CuentaCorriente{
    Scanner scanner = new Scanner(System.in);
    Random random = new Random();
    int dormir =random.nextInt(1000-250+1)+250;
    int SaldoCuenta;

    public int getSaldoCuenta() {
        try {
            Thread.sleep(dormir);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return SaldoCuenta;
    }

    public void setSaldoCuenta(int saldoCuenta) {
        SaldoCuenta = saldoCuenta;
        try {
            Thread.sleep(dormir);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized void anadirSaldo(){
        System.out.println("Quien esta realizando el ingreso ");
        String nombre = scanner.nextLine();
        System.out.println("El saldo actual es de "+getSaldoCuenta());
        System.out.println("Cuanto deseas añadir al saldo ");
        int anadir_saldo = scanner.nextInt();
        int nuevo_saldo = getSaldoCuenta()+anadir_saldo;
        setSaldoCuenta(nuevo_saldo);
        System.out.println(""+nombre+" ha realizado un ingreso de "+anadir_saldo+ " y el saldo final es de "+getSaldoCuenta());
    }
}
