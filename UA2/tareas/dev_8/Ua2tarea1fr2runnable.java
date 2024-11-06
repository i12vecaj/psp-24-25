import java.util.Random;

public class Ua2tarea1fr2runnable {
    public static void main(String[] args) {

        // creo los objetos
        Atraccion atraccion1 = new Atraccion("Montaña Rusa");
        Atraccion atraccion2 = new Atraccion("Casa del Terror");
        Atraccion atraccion3 = new Atraccion("Río Lento");
        Atraccion atraccion4 = new Atraccion("DR.Calambre");
        Atraccion atraccion5 = new Atraccion("CECOT");

        // creo hilos y los inicio
        Thread hilo1 = new Thread(atraccion1);
        Thread hilo2 = new Thread(atraccion2);
        Thread hilo3 = new Thread(atraccion3);
        Thread hilo4 = new Thread(atraccion4);
        Thread hilo5 = new Thread(atraccion5);


        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {
            // esperamos a que terminen todos los hilos
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Todas las Atracciones han terminado AAAAAAYPS");
    }
}

class Atraccion implements Runnable {
    private String nombre;

    public Atraccion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        int tiempo = 5000 + random.nextInt(8000);

        synchronized (this) {
            System.out.println("La atracción '" + nombre + "' comienza, tiempo: " + (tiempo / 1000) + " segundos.");
        }

        try {
            System.out.println("Hilo en ejecucion " + Thread.currentThread().getName()); // control de errores
            Thread.sleep(tiempo); //aqui hago que espere el tiempo que muestra por pantalla
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        synchronized (this) {
            System.out.println("La atracción '" + nombre + "' ha terminado.");
        }
    }
}
