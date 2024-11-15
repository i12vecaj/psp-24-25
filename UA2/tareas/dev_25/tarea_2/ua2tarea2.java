import java.util.Random;

public class ua2tarea2 {
    public static void main(String[] args) {
        // Se crea una cuenta corriente inicializada con 1000 euros
        CuentaCorriente cuenta = new CuentaCorriente(1000);

        // Se crean tres hilos para incrementar el saldo de la cuenta
        HiloIncrementar hilo1 = new HiloIncrementar(cuenta, 200);
        HiloIncrementar hilo2 = new HiloIncrementar(cuenta, 300);
        HiloIncrementar hilo3 = new HiloIncrementar(cuenta, 400);

        // Asignamos nombres a los hilos para identificarlos
        hilo1.setName("Hilo 1");
        hilo2.setName("Hilo 2");
        hilo3.setName("Hilo 3");

        // Iniciamos los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}

class CuentaCorriente {
    // Objeto para generar tiempos aleatorios de espera
    Random random = new Random();
    // Atributo que almacena el saldo de la cuenta
    private int saldo;

    // Constructor para inicializar la cuenta con un saldo determinado
    public CuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    // Método sincronizado para obtener el saldo de la cuenta
    public synchronized int getSaldo() {
        esperar();  // Simula el tiempo de espera aleatorio
        return saldo;
    }

    // Método sincronizado para establecer un nuevo saldo en la cuenta
    public synchronized void setSaldo(int saldo) {
        esperar();  // Simula el tiempo de espera aleatorio
        this.saldo = saldo;
    }

    // Método privado que simula un tiempo de espera aleatorio
    private void esperar() {
        // Genera un tiempo aleatorio entre 250 y 2250 ms
        int tiempoEspera = 250 + random.nextInt(2000);
        try {
            // El hilo se duerme por el tiempo calculado
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Método sincronizado que incrementa el saldo de la cuenta
    public synchronized void incrementar(double cantidad) {
        // Muestra el saldo actual antes de comenzar el incremento
        System.out.println("El saldo actual es de : " + saldo);

        // Incrementa el saldo 1000 veces
        for (int i = 0; i < 1000; i++) {
            // Si el saldo es múltiplo de 200, se imprime el mensaje
            if (saldo % 200 == 0) {
                System.out.println(Thread.currentThread().getName() + " ha llegado a " + saldo + " euros");
            }
            // Se incrementa el saldo en 1 cada vez
            saldo++;
        }

        // Una vez que el hilo termina de incrementar el saldo, se imprime el resultado final
        System.out.println(Thread.currentThread().getName() + " ha terminado con " + saldo + " euros");
    }
}

class HiloIncrementar extends Thread {
    // La cuenta sobre la que trabajará el hilo
    private CuentaCorriente cuenta;
    // Cantidad que se incrementará, aunque no se usa directamente
    private double cantidad;

    // Constructor que recibe la cuenta y la cantidad a incrementar
    public HiloIncrementar(CuentaCorriente cuenta, double cantidad) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    // El método run() se ejecuta cuando el hilo comienza su trabajo
    @Override
    public void run() {
        // Llama al método incrementar de la clase CuentaCorriente
        cuenta.incrementar(cantidad);
    }
}