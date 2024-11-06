class Contador {
    private int c = 0;

    Contador(int c) { 
        this.c = c;
    }

    // Método sincronizado para incrementar el contador
    public synchronized void incrementa() {
        c += 1000;
    }

    public int valor() {
        return c;
    }
} 


class HiloIncrementador implements Runnable {
    private Contador contador;
    private String nombre;

    public HiloIncrementador(String nombre, Contador c) {
        this.nombre=nombre;
        this.contador = c;
    }

    public void run() {
        contador.incrementa();
        System.out.println(this.nombre + " - contador ahora vale " + contador.valor());
    }
}


public class ua2tarea1fr2runnable {
    public static void main(String[] args) {

        Contador cont = new Contador(0);

        Thread hilo1 = new Thread (new HiloIncrementador("Hilo 1", cont));
        Thread hilo2 = new Thread (new HiloIncrementador("Hilo 2", cont));
        Thread hilo3 = new Thread (new HiloIncrementador("Hilo 3", cont));
        Thread hilo4 = new Thread (new HiloIncrementador("Hilo 4", cont));
        Thread hilo5 = new Thread (new HiloIncrementador("Hilo 5", cont));


        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}
