package ua2tarea2_javiergarcia;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//Se crea la clase en la que estará el saldo de la cuenta.
class CuentaCorriente{
    int saldo;

    public CuentaCorriente(int saldo) {
        this.saldo = saldo;
    }

    public synchronized int getSaldo(){
     
        try {
            Thread.sleep((int) (Math.random()*(1751)+250));
        } catch (InterruptedException ex) {
            Logger.getLogger(CuentaCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }
     
        return saldo;
    }

    public synchronized void setSaldo(int saldo){
        
        try {
            Thread.sleep((int) (Math.random()*(1751)+250));
        } catch (InterruptedException ex) {
            Logger.getLogger(CuentaCorriente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.saldo = saldo;
    }
    //Se suma el saldo al total, se muestra el anterior y quien ha realizado la operacion
    public synchronized void añadirSaldo(int añadir, String nombre){ //comentar synchronized y probar el código y cambiar la variable en el main
        System.out.println("La cuenta tenía un saldo de "+this.saldo);
        
        this.saldo+=añadir;
        
        System.out.println("Su nuevo saldo es "+this.saldo);
        System.out.println("Operación realizada por " +nombre);
    }
}
//clase para los hilos que desplegaremos
class Usuario extends Thread{

    String nombre;
    int dinero;
    CuentaCorriente cuenta;

    public Usuario(String nombre, int dinero, CuentaCorriente cuenta) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.cuenta = cuenta;
    }

    public void run(){
        cuenta.añadirSaldo(dinero, nombre);
    }
}


public class Ua2tarea2_JavierGarcia {

      public static void main(String[] args) {
        //Creamos e inicializamos la cuenta a 200
        CuentaCorriente cuenta = new CuentaCorriente(200);
        
        //Creamos la lista de hilos que funcionarán en el programa
        ArrayList<Thread> Hilos = new ArrayList<Thread>(); 
        
        for(int i=0; i<5; i++)
        {
            //aleatorizamos el dinero con el que cuenta cada hilo
            int dinero = (int) (Math.random()*(151)+50);
            //creamos el hilo con su nombre, dinero inicial y pasándole la cuenta común a todos los hilos
            Usuario user = new Usuario("Hilo "+i,dinero*i,cuenta);
            Hilos.add(new Thread(user)); 
        }
        //Inicializamos cada hilo
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
            catch (InterruptedException e){}
        }
         //mostramos lo que nos pide el ejercicio
         System.out.println("El total en la cuenta es de "+ cuenta.getSaldo());
         System.out.println("Al quitar el synchronized, los mensajes se nos muestran en desorden.\n Las operaciones se realizan de forma asíncrona");
    }
    
}

