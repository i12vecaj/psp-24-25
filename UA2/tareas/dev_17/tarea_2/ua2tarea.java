import java.util.Random;

public class Main {
    public static void main(String[] args) {

        //Creamos una instancia de CuentaCorriente con saldo inicial en 0
        CuentaCorriente cuenta = new CuentaCorriente();

        //Mostramos el saldo inicial
        System.out.println("Saldo inicial: " + cuenta.getSaldo() + "\n");

        //Creamos tres hilos que incrementarán el saldo en cantidades diferentes
        Thread hilo1 = new Thread(new HiloIncrementador(cuenta, 200), "Hilo Numero 1");
        Thread hilo2 = new Thread(new HiloIncrementador(cuenta, 400), "Hilo Numero 2");
        Thread hilo3 = new Thread(new HiloIncrementador(cuenta, 600), "Hilo Numero 3");

        //Iniciamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        //Esperamos a que todos los hilos terminen su ejecución
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Mostramos el saldo final después de que todos los hilos terminen
        System.out.println("\nSaldo final: " + cuenta.getSaldo());
    }
}

class CuentaCorriente {
    private int saldo; //Variable para almacenar el saldo de la cuenta

    public CuentaCorriente() {//Constructor inicializamos el saldo a 0
        this.saldo = 0;
    }

    public int getSaldo() {
        dormir(); //Simulamos un retraso en la operación
        return saldo;
    }
    public void setSaldo(int saldo) {
        dormir(); //Simulamos un retraso en la operación
        this.saldo = saldo;
    }


    public synchronized void incrementSaldo(int cantidad) { // Aqui usamos syncronized para sincronizar los hilos.
        System.out.println("El saldo actual es de: " + saldo + ".");

        for (int i = 0; i < cantidad; i++) {//Incrementa el saldo en un bucle, uno por uno
            //Si el saldo es múltiplo de 200, muestra un mensaje
            if (saldo % 200 == 0) {
                System.out.println(Thread.currentThread().getName() + " ha llegado a: " + saldo + " de saldo.");
            }
            saldo++; //Incrementa el saldo
        }

        System.out.println(Thread.currentThread().getName() + " ha terminado con " + saldo + " de saldo.");//Muestra un mensaje indicando que el hilo ha terminado su tarea
    }

    private void dormir() {//Aqui tenemos a parte el metodo dormir para darle un tiempo especifico de dormir a los hilos
        Random rand = new Random();
        int duracion = rand.nextInt(2000) + 250;// Duración aleatoria entre 250 y 2250 ms
        try {
            Thread.sleep(duracion); //Detiene la ejecución durante la duración generada
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


class HiloIncrementador extends Thread { // Clase que implementa un hilo para incrementar el saldo

    private final CuentaCorriente cuenta; //Referencia a la cuenta que será modificada
    private final int cantidad; //Cantidad a incrementar en la cuenta

    //Recibimos la cuenta y la cantidad que se debe incrementar
    public HiloIncrementador(CuentaCorriente cuenta, int cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    //Ejecutamos el hilo
    @Override
    public void run() {
        cuenta.incrementSaldo(cantidad); //Incrementa el saldo de la cuenta
    }
}

/*La diferencia entre implementar syncronized o no es que los hilos comenzaran a usar los metodos sin esperar a que cada uno acabe su funcion,
* mientras que con syncronidez los hilos entran en los metodos de forma ordenada acabando cada uno su funcion.*/