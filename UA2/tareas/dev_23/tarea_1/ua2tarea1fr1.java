//En este codigo podemos observar al ejecutarlo como todos los hilos acceden a la vez al contador y por ello el valor del contador en cada hilo no coincide con el numero de hilo que es
class Contador
{
    private int c = 0;  // Atributo Contador
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


class HiloRestador extends Thread 
{
    private Contador contador;

    public HiloRestador(String nombre, Contador c) {
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
        HiloRestador hiloResta1 = new HiloRestador("Hilo Restador 1", cont);
        HiloRestador hiloResta2 = new HiloRestador("Hilo Restador 2", cont);
        HiloRestador hiloResta3 = new HiloRestador("Hilo Restador 3", cont);
        HiloRestador hiloResta4 = new HiloRestador("Hilo Restador 4", cont);
        HiloRestador hiloResta5 = new HiloRestador("Hilo Restador 5", cont);
        
        System.out.println("Comienza la ejecución de los hilos ...");
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

