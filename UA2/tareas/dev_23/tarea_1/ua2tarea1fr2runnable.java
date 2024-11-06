class Contador
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public synchronized void incrementa() {
        c+=1000;
    }

    public int valor() {
        return c;
    }
} // Fin Class Contador


class HiloRestador implements Runnable 
{   
    private String nombre;
    private Contador contador;

    public HiloRestador(String nombre, Contador c) {
        this.nombre=nombre;
        contador = c;
    }

    public void run() {
      contador.incrementa();
      System.out.println(nombre + " - contador vale " + contador.valor());
    }
} // Fin Class HiloRestador


public class ua2tarea1fr2runnable {
    public static void main(String[] args) {

        Contador cont = new Contador(0);
        Thread hiloResta1 = new Thread(new HiloRestador("Hilo Restador 1", cont));
        Thread hiloResta2 = new Thread(new HiloRestador("Hilo Restador 2", cont));
        Thread hiloResta3 = new Thread(new HiloRestador("Hilo Restador 3", cont));
        Thread hiloResta4 = new Thread(new HiloRestador("Hilo Restador 4", cont));
        Thread hiloResta5 = new Thread(new HiloRestador("Hilo Restador 5", cont));
        
        
        System.out.println("Comienza la ejecución sincronizada con runnable de los hilos ...");
        System.out.println("--------------------------------------");
        hiloResta1.start();

        hiloResta2.start();

        hiloResta3.start();

        hiloResta4.start();

        hiloResta5.start();

        try {
          hiloResta1.join();
          hiloResta2.join();
          hiloResta3.join();
          hiloResta4.join();
          hiloResta5.join();
      }
      catch (InterruptedException e)
      {
          // Nothing to do here ...
      }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
    }
}


