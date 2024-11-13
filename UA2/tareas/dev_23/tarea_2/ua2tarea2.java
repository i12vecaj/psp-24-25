import java.util.Random;

public class ua2tarea2 {
    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente();
        System.out.println("------------------------------------\n");
        System.out.println("Saldo inicial: " + cuenta.getSaldo() + "€");
        System.out.println("\n-------------------------------------\n");

        //Creamos los hilos Con diferentes nombres para identificarlos y el objeto cuenta, para que todos actuen sobre la misma variable
        Thread ingreso1 = new sumador("Juan", cuenta);
        Thread ingreso2 = new sumador("Javier", cuenta);
        Thread ingreso3 = new sumador("Felipe", cuenta);

        //Iniciamos los hilos
        ingreso1.start();
        ingreso2.start();
        ingreso3.start();

        //Esperamos a que los hilos terminen hasta para mostrar el mensaje de finalización
        try {
            ingreso1.join();
            ingreso2.join();
            ingreso3.join();
        } catch (Exception e) {
          
        }

        System.out.println("Ejecución finalizada");
    }
}

class CuentaCorriente {
    private double saldo;

    CuentaCorriente() {
        saldo = 200;
    }

    /*Creamos el metodo sumarSaldo en el que se le pasa un saldo aleatorio para sumar y el nombre del hilo que lo va a sumar
     * a continuacion Indica que hilo va a sumar y cuanto va a sumar y lo suma
     * seguidamente se llama a la funcion simularRetraso() y posteriormente muestra el saldo con el incremente realizado.
     * 
     * En cuanto al uso del parametro synchronized, este permite que los hilos se ejecuten de uno en uno con el valor de la variable correspondiente, aunque no haya orden,
     * mientras que si este parametro se quita, primero marcara cuanto dinero va a ingresar cada hilo
     * y despues todas las comparaciones de saldo son iguales ya que se ha sumado todo de golpe
     */
    public synchronized void sumarSaldo(double saldo, String nombre) {
        System.out.println(nombre + " va a realizar un ingreso de " + saldo + "€");
        System.out.println("\n-------------------------------------\n");
        System.out.println("Saldo anterior: " + getSaldo() + "€");
        this.saldo += saldo;
        simularRetraso();
        System.out.println("Saldo actual: " + getSaldo() + "€");
        System.out.println("\n-------------------------------------\n");
    }

    public double getSaldo() {
        return saldo;
    }

    /*Esta funcion simula un retraso de entre 0,25s y 2 segundos en el ingreso del dinero
     * durmiendo el hilo durante un tiempo aleatorio entre dichos parametros
    */
    public void simularRetraso() {
        Random random = new Random();
        long tiempoRetraso = random.nextLong(250, 2000);
        try {
            Thread.sleep(tiempoRetraso);
        } catch (Exception e) {
            
        }
    }
}


class sumador extends Thread {
    private CuentaCorriente cuenta;
    private String nombre;

    sumador(String nombre, CuentaCorriente cuenta) {
        this.nombre = nombre;
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        Random random = new Random();
        //Redondeo un numero aleatorio entre 100 y 500€ a dos decimales y este numero va a ser el que se va a sumar al saldo de la cuenta corriente
        double cantidad = Math.round(random.nextDouble(100, 500)*100.0)/100.0;
        cuenta.sumarSaldo(cantidad, nombre);
    }
}
