//En este codigo al tener los hilos sincronizados no atacan a la misma vez al contador y por consecuencia, los hilos tienen el valor que le pertenece
//por ejemplo el hilo 1 1000 etc, aunque los inprima desordenados, cada uno tiene el valor que le corresponde

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


class HiloIncrementador extends Thread {
    private Contador contador;

    public HiloIncrementador(String nombre, Contador c) {
        setName(nombre);
        this.contador = c;
    }

    public void run() {
        contador.incrementa();
        System.out.println(getName() + " - contador ahora vale " + contador.valor());
    }
}


public class ua2tarea1fr2 {
    public static void main(String[] args) {

        Contador cont = new Contador(0);

        HiloIncrementador hilo1 = new HiloIncrementador("Hilo 1", cont);
        HiloIncrementador hilo2 = new HiloIncrementador("Hilo 2", cont);
        HiloIncrementador hilo3 = new HiloIncrementador("Hilo 3", cont);
        HiloIncrementador hilo4 = new HiloIncrementador("Hilo 4", cont);
        HiloIncrementador hilo5 = new HiloIncrementador("Hilo 5", cont);

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
