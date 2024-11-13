class Contador
{
    private int c = 0;  //iniciamos Contador
    Contador (int c) { 
        this.c = c;
    }

    public void incrementa() {
        c+=1000;
    }

    public int valor() {
        return c;
    }
} // Fin Class Contador


class hiloRest extends Thread 
{
    private Contador contador;

    public hiloRest(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        contador.incrementa();
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
} // Fin Class HiloRestador


public class ua2tarea1fr1 {
    public static void main(String[] args) {

        Contador cont = new Contador(0);
        hiloRest hiloRest1 = new hiloRest("Hilo Restador 1", cont);
        hiloRest hiloRest2 = new hiloRest("Hilo Restador 2", cont);
        hiloRest hiloRest3 = new hiloRest("Hilo Restador 3", cont);
        hiloRest hiloRest4 = new hiloRest("Hilo Restador 4", cont);
        hiloRest hiloRest5 = new hiloRest("Hilo Restador 5", cont);
       
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
