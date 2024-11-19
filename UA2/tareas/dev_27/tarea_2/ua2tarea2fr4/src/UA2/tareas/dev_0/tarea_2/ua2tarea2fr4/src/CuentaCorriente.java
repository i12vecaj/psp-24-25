package UA2.tareas.dev_0.tarea_2.ua2tarea2fr4.src;
import java.util.Scanner;

public class CuentaCorriente {
    private final Scanner scanner = new Scanner(System.in);
    private int SaldoCuenta;

    public synchronized int getSaldoCuenta() {
        return SaldoCuenta;
    }

    public synchronized void setSaldoCuenta(int saldoCuenta) {
        SaldoCuenta = saldoCuenta;
    }

    public synchronized void anadirSaldo() {
        System.out.println("¿Quién está realizando el ingreso?");
        String nombre = scanner.nextLine();
        System.out.println("El saldo actual es de " + getSaldoCuenta());
        System.out.println("¿Cuánto deseas añadir al saldo?");
        int anadirSaldo = scanner.nextInt();
        scanner.nextLine();
        int nuevoSaldo = getSaldoCuenta() + anadirSaldo;
        setSaldoCuenta(nuevoSaldo);
        System.out.println(nombre + " ha realizado un ingreso de " + anadirSaldo +
                " y el saldo final es de " + getSaldoCuenta());
    }
}
