import java.util.Random;

//FR4:Cuando el synchronized desaparece ambas ejecuciones del hilo repiten el apartado de dinero ingresado tantas veces como hilos haya inicializados
//contrario a cuando esta en sinchronized lo que hace que la funcion ingresar se termine en un hilo antes de iniciar otro.
public class ua2tarea2 {
    public static void main(String[] args) {

        // le asignamos un saldo inicial para la funcion ingresar.
        CuentaCorriente cuenta = new CuentaCorriente(1000);

        // indicamos tanto la cantidad ingresada como la persona que lo ingreso.
        HiloIngreso hilo1 = new HiloIngreso(cuenta, 500, "Usuario1");
        HiloIngreso hilo2 = new HiloIngreso(cuenta, 300, "Usuario2");

        hilo1.start();
        hilo2.start();

        //control del momento de inicializacion de los hilos.
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class CuentaCorriente {
    private double saldo;

    public CuentaCorriente(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    // funcion get y le añadimos la funcion randomSleep.
    public  double getSaldo() {
        randomSleep();
        return saldo;
    }

    // funcion set y le añadimos la funcion randomSleep.
    public  void setSaldo(double saldo) {
        randomSleep();
        this.saldo = saldo;
    }

    // Con esta funcion podemos ingresar dinero ,saber quien lo ha ingresado y cual es saldo tras el ingreso
    // esta funcion en si no nos permite ingresar las cantidades sino que lo hacemos desde el llamamiento de los hilos.
    public synchronized void ingresar(double cantidad, String usuario) {
        double saldoInicial = getSaldo();
        System.out.println(usuario + " está realizando un ingreso de " + cantidad + ". Saldo inicial: " + saldoInicial);
        setSaldo(saldoInicial + cantidad);
        double saldoFinal = getSaldo();
        System.out.println("Ingreso realizado por " + usuario + ". Saldo final: " + saldoFinal);
    }

    // aqui creamos una funcion para asignar un lapso de tiempo aleatorio para el setsaldo y el getsaldo.
    private void randomSleep() {
        Random random = new Random();
        int delay = 250 + random.nextInt(1751);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// clase Hilo en el cual creamos un constructor con todos los datos para usar la funcion ingresar y  ejecutamos la funcion ingresar.
class HiloIngreso extends Thread {
    private final CuentaCorriente cuenta;
    private final double cantidad;
    private final String usuario;

    public HiloIngreso(CuentaCorriente cuenta, double cantidad, String usuario) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    @Override
    public void run() {
        cuenta.ingresar(cantidad, usuario);
    }
}