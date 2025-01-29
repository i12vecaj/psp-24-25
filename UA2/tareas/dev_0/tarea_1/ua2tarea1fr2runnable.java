class Contador
{
    private int c = 0;
    Contador (int c) { 
        this.c = c;
    }

    public synchronized void incrementa() {
        c+=1000;
    }

    public int valor() {
        return c;
    }
}


class hiloRest implements Runnable 
{   
    private String nombre;
    private Contador contador;

    public hiloRest(String nombre, Contador c) {
        this.nombre=nombre;
        contador = c;
    }

    public void run() {
      contador.incrementa();
      System.out.println(nombre + " - contador vale " + contador.valor());
    }
}


public class ua2tarea1fr2runnable {
    public static void main(String[] args) {

        Contador cont = new Contador(0);
        Thread hiloRest1 = new Thread(new hiloRest("Hilo 1", cont));
        Thread hiloRest2 = new Thread(new hiloRest("Hilo 2", cont));
        Thread hiloRest3 = new Thread(new hiloRest("Hilo 3", cont));
        Thread hiloRest4 = new Thread(new hiloRest("Hilo 4", cont));
        Thread hiloRest5 = new Thread(new hiloRest("Hilo 5", cont));
        
        hiloRest1.start();
        hiloRest2.start();
        hiloRest3.start();
        hiloRest4.start();
        hiloRest5.start();

        try {
          hiloRest1.join();
          hiloRest2.join();
          hiloRest3.join();
          hiloRest4.join();
          hiloRest5.join();
      }
      catch (InterruptedException e)
      {
          // Nothing to do here ...
      }
        System.out.println("Finaliza la ejecución de los hilos");
        System.out.println("Valor Final del Contador: " + cont.valor());
    }
}
