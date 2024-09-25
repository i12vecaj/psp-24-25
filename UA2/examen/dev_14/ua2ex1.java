/*
 * parte: FR1
 * @class vasoCerveza
 * @atributos: idvaso,pinta,mediapinta,disponible
 * 
 */

class vasoCerveza{
    
    private int idvaso;
    private int pinta;
    private int mediapinta;
    private boolean disponible = false; 
    
    public synchronized int get() {
        while(!disponible) {        
            try {
                wait();             
            } catch(InterruptedException e) {}            
        }
        int idvaso_tmp = 0;
        if(idvaso == 6)
        {
            idvaso_tmp = idvaso;
            idvaso = 0;
        }
        System.out.println( "Consume: " + idvaso_tmp);
        disponible = false; // Se pone la cola vacía ...
        notify();           // El objeto sincronizado queda libre
        return idvaso_tmp;       // Se devuelven los vasos
    }
 
    
    public vasoCerveza(int idvaso, int tipo) {
        this.idvaso = idvaso;
        this.pinta = pinta;
        this.mediapinta = mediapinta;
        
    
    }

    public synchronized int getIdvaso() {
        return idvaso;
    }

    public void setIdvaso(int idvaso) {
        this.idvaso = idvaso;
    }

    public int getPinta() {
        return pinta;
    }

    public void setPinta(int pinta) {
        this.pinta = pinta;
    }
      public void setMediaPinta(int mediapinta) {
        this.mediapinta = mediapinta;
    }
       public int getMediapinta() {
        return mediapinta;
    }

    
    
}

/*
 * parte: FR2
 * @class Camarero
 * @atributos: listaVasos
 * 
 */


class  Camarero {
    
    int listaVasos;

    public int getListaVasos() {
        return listaVasos;
    }

    public void setListaVasos(int listaVasos) {
        this.listaVasos = listaVasos;
    }

    public Camarero(int listaVasos) {
        this.listaVasos = listaVasos;
    }

    public void servirCerveza(){
    
    
}
     public void devolverCerveza(){
    
    
}
      public void contarVasos(){
    
    
}
}


/*
 * parte: FR3
 * @class HilosClientes
 * @atributos: vaso,n
 * 
 */


class HilosClientes extends Thread 
{
    private vasoCerveza vaso;
    private int n;

    public HilosClientes(String string, vasoCerveza cont) {
    }

    public void HilosCLientes (vasoCerveza c, int n) {
        vaso = c;
        this.n = n;
    }

    public void run() { 
        int valor = 0;       
        for(int i=0; i<10;i++)
        {
            valor = vaso.get(); // Pongo cervezas
            //System.out.println(i + " => Cliente : "  + n + " consume: " + valor);

            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
        }
    }
}
class ExecutionTimer
{
    private long startTime = 0;
    private long endTime = 0;
    private long timeElapsed = 0;
        
    public void start() {
        startTime = System.nanoTime();
    }
    
    public void stop() {
        endTime = System.nanoTime();
    }

    public void elapsedTime() {
        timeElapsed = endTime - startTime;
    }

    public void printElapsedTime()    
    {
        elapsedTime();
        System.out.println("Execution time in nanoseconds: " + timeElapsed);
        System.out.println("Execution time in milliseconds: " + timeElapsed / 1000000);
    }
}

/*
 * parte: FR4
 * @class ua2ex1
 * 
 * 
 */

public class ua2ex1 {

    
    public static void main(String[] args) {
         
        
        System.out.println("-------------------------------");
        System.out.println("HilosClientess: Bloques NO Sincronizados");
        System.out.println("-------------------------------");
 

        vasoCerveza cont = new vasoCerveza(100, 0);
        
        HilosClientes HilosClientes1 = new HilosClientes("HilosClientes 1", cont);
        HilosClientes HilosClientes2 = new HilosClientes("HilosClientes 2", cont);
        HilosClientes HilosClientes3 = new HilosClientes("HilosClientes 3", cont);
        HilosClientes HilosClientes4 = new HilosClientes("HilosClientes 4", cont);
        HilosClientes HilosClientes5 = new HilosClientes("HilosClientes 5", cont);
      
        System.out.println("Comienza la ejecución de los HilosClientess ...");
        System.out.println("--------------------------------------");
        HilosClientes1.start();
        
        HilosClientes2.start();
        
        HilosClientes3.start();
        
        HilosClientes4.start();
        
        HilosClientes5.start();
        

        try {
        HilosClientes1.join();
        HilosClientes2.join();
        HilosClientes3.join();
        HilosClientes4.join();
        HilosClientes5.join();
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los HilosClientess");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.getIdvaso());
        System.out.println("--------------------------------------");
   
        
    }
}
    
