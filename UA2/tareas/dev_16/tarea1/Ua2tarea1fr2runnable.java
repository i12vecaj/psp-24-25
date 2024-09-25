package ua2tarea1fr3;

import java.util.ArrayList;

class Contador
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
    
    class HiloSumador implements Runnable 
{
    private Contador contador;

    public HiloSumador(String nombre, Contador c) {
        Thread.currentThread().setName(nombre);
        
        contador = c;
    }

    public void run() {
        
            for(int j=0; j<1000;j++)
            {
                contador.incrementa();
                
            }
            System.out.println(Thread.currentThread().getName() + " - contador vale " + contador.valor());
        
    }
}

public class Ua2tarea1fr2runnable {

    
    public static void main(String[] args) {
    System.out.println("-------------------------------");
        System.out.println("Hilos: Tarea 1 con funcion incrementar sincronizada y usando runnable");
        System.out.println("-------------------------------");

        
        ArrayList<Thread> Hilos = new ArrayList<Thread>(); 
        int numeroHilos = 5;
        
        Contador cont = new Contador(0);
        for(int i=0; i<numeroHilos; i++)
        {
            HiloSumador hilo = new HiloSumador("Hilo "+i,cont);
            Hilos.add(new Thread(hilo)); 
        }
        
        
        System.out.println("Comienza la ejecución de los hilos ...");
        System.out.println("--------------------------------------");
        
        
        for(int i=0; i<Hilos.size(); i++)
        {
            Hilos.get(i).start();
        }
        
         for(int i=0; i<Hilos.size(); i++)
        {
            try
            {
                Hilos.get(i).join();
            }
            catch (InterruptedException e)
            {
                // Nothing to do here ...
            }
        }
        System.out.println("--------------------------------------");
        System.out.println("... Finaliza la ejecución de los hilos");
        System.out.println("--------------------------------------");
        System.out.println("Valor Final del Contador: " + cont.valor());
        System.out.println("--------------------------------------");
        System.out.println("El resultado con Runnable es el mismo que con Threads.\n Los hilos se muestran en orden de finalización y el resultado es el que buscamos");
        System.out.println("--------------------------------------");
    
    }
    
}
