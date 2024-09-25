package ua2tarea1fr2;

class Contador //se declara una clase contador para que el valor se incremente
{
    private int c = 0;  // Atributo Contador
    Contador (int c) { 
        this.c = c;
    }

    public void incrementa() {
        c++;
    }

    public void decrementa() {
        c--;
    }

    public int valor() {
        return c;
    }
} // Fin Class Contador
    
    class HiloSumador extends Thread //Se declara la clase HiloSumador donde el valor de los hilos se va a ir incrementando
{
    private Contador contador;

    public HiloSumador(String nombre, Contador c) {
        setName(nombre);
        contador = c;
    }

    public void run() {
        //con el synchronized hacemos que el valor de los hilos se sumen sincronizadamente, por ejemplo, 1000, 2000, 30000, ...
        synchronized(contador){
        for(int j=0; j<1000;j++) // TODO: Cambia a 1000 en ambos a ver qué ocurre ...
        {
            contador.incrementa();
            /*
            try {
                sleep(100);
            }
            catch (InterruptedException e) {}
            */
        }
        System.out.println(getName() + " - contador vale " + contador.valor());
    }
    }
} 

public class ua2tarea1fr2 {

    
    public static void main(String[] args) {
    System.out.println("-------------------------------");
        System.out.println("Hilos: Bloques Sincronizados");
        System.out.println("-------------------------------");

        //se crea el contador y los hilos necesarios
        Contador cont = new Contador(0);
        HiloSumador hilo1 = new HiloSumador("Hilo 1", cont);

        HiloSumador hilo2 = new HiloSumador("Hilo 2", cont);

        HiloSumador hilo3 = new HiloSumador("Hilo 3", cont);

        HiloSumador hilo4 = new HiloSumador("Hilo 4", cont);

        HiloSumador hilo5 = new HiloSumador("Hilo 5", cont);
        
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");//Se inician los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        
        
        try {
             //Los hilos van recogiendo el valor que se le asigna
               hilo1.join();
               
            
               hilo2.join(); 
            
            
               hilo3.join(); 
            
            
               hilo4.join(); 
            
            
               hilo5.join(); 
            
            
        }
        catch (InterruptedException e)
        {
            // Nothing to do here ...
        }

        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador1: " + cont.valor());
        System.out.println("--------------------------------------");
        
        System.out.println("Se obtiene el resultado esperado porque los hilos están sincronizados entre sí mediante synchronized(contador)");
        System.out.println("La diferencia entre apartados es que en el primero los valores de los hilos son aleatorios y en el segundo tienen un orden.");
        
    
    }
    
    }
    
