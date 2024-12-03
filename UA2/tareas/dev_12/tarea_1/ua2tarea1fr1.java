//En este codigo todos los hilos utilizan el contador a la vez por lo que varios hilos pueden tpmar un valor que no le pertemece o incluso varios 
//pueden tener el mismo valor

class Contador {
    private int c = 0;

    Contador(int c) { 
        this.c = c;
    }

    public void incrementa() {
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


public class ua2tarea1fr1 {
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


        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}
