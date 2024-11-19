package UA2.tareas.dev_0.tarea_2;

public class ua2tarea2 {
    public static void main(String[] args) throws InterruptedException {
        CuentaCorriente cuenta = new CuentaCorriente(); //Instanciamos el objeto cuenta
        System.out.println("Saldo inicial " + cuenta.getSaldo());
        Hilo hilo1 = new Hilo(50, "Luis", cuenta); //Creamos los 3 hilos y pasamos los argumentos necesarios
        Hilo hilo2 = new Hilo(23, "Francisco", cuenta);
        Hilo hilo3 = new Hilo(16, "Manuel", cuenta);

        hilo1.start(); //Iniciamos los 3 hilos
        hilo2.start();
        hilo3.start();

        hilo1.join(); //Sincornizamos la ejecución de los hilos
        hilo2.join();
        hilo3.join();

        System.out.println("\nSalgo final: " + cuenta.getSaldo());
    }
} //Cuando quitamos el synchronized podemos observar que los distintos procesos se solapan al ejecutar el método y pierde la consistencia del resultado
  //mostrado por pantalla
  
class Hilo extends Thread {
    private CuentaCorriente cuenta;
    private double importe;
    private String nombre;

    public Hilo (int importe, String nombre, CuentaCorriente cuenta) {
        if (!(importe <= 0)) { //Hacemos un control de errores para evitar valores negativos
            this.importe = importe;
            this.nombre = nombre;
            this.cuenta = cuenta;
        } else {
            throw new IllegalArgumentException("El importe debe de ser superior a 0€");
        }
    }

    @Override
    public void run() {
        cuenta.addSaldo(importe, nombre);
    }
}

class CuentaCorriente {
    private double saldo;
    CuentaCorriente(){
        saldo = 0;
    }

    public void setSaldo(double saldo) throws InterruptedException {
        System.out.println("Setting Saldo...");
        Thread.sleep((long) ((Math.random()*2000)+250));
        System.out.println("Operation complete!");
        this.saldo = saldo;
    }

    public double getSaldo() throws InterruptedException {
        System.out.println("Getting Saldo...");
        Thread.sleep((long) ((Math.random()*2000)+250));
        System.out.println("Operation complete!");
        return saldo;
    }

    public synchronized void addSaldo(double cantidad, String usuario) {
        System.out.print("Estado inicial de saldo " + saldo + "€ ");
        saldo += cantidad;
        System.out.println("| Transferencia de " + cantidad + "€ realizada por " + usuario + " | Saldo total de " + saldo + "€");

    }
}