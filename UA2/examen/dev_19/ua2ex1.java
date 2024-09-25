//Creamos la clase vasoCerveza
class vasoCerveza{
    
    private int idvaso;
    private int pinta;
    private int mediapinta;
    private boolean disponible = false; // Inicilmente la cola debe estar vacía
    
    public synchronized int get() {
        while(!disponible) {        // Si hay cerveza ...
            try {
                wait();             // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}            
        }
        int idvaso_tmp = 0;
        if(idvaso == 6)
        {
            idvaso_tmp = idvaso;
            idvaso = 0;
        }
        System.out.println( "Se consume: " + idvaso_tmp);
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

//Creamos la clase camarero

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


//Creamos los Hilos

class HilosClientes extends Thread 
{
    private vasoCerveza vaso;
    private int n;

    public HilosClientes(vasoCerveza c, int n) {
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

public class Ua2ex1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("-----------------------------------");
        System.out.println("Hilos: Productor - Consumidor Final");
        System.out.println("-----------------------------------");
        ExecutionTimer timer = new ExecutionTimer();

        

        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
       

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        
        timer.stop();
        timer.printElapsedTime();
    }
        
        
        
        // TODO code application logic here
    }
