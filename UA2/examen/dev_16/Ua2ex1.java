package ua2ex1;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;




class vasoCerveza {
    private int id;
    private int tipo=(int) (Math.random()*1);

    public vasoCerveza(int id) {
        this.id = id;
        this.tipo = (int) (Math.random()*1);
    }

    public int getId() {
        return id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}


class Camarero{
    private String nombre;  // Datos a Producir por los Hilos Productores y a Consumir por los Hilos Consumidores
    ArrayList<vasoCerveza> listaVasos=new ArrayList<vasoCerveza>(); // Inicilmente la cola debe estar vacía
    private boolean disponible = false;
    
    public Camarero(String nombre) {
        this.nombre = "Mou";
        
        
        for(int i=0;i<3;i++){
        vasoCerveza vaso = new vasoCerveza(i);
        listaVasos.add(vaso);
        }
    }
    
    public synchronized vasoCerveza servirCerveza() {
        while(!disponible) {        // Si hay datos ...
            try {
                wait();             // Espero a poder acceder al objeto sincronizado
            } catch(InterruptedException e) {}            
        }
        
        for(int i=0;i<3;i++){
            System.out.println( "Vaso de cerveza " + listaVasos.get(i) +" servida");
            disponible = false; // Se pone la cola vacía ...
            notify();
        }
                   // El objeto sincronizado queda libre
        return listaVasos.get(0);       // Se devuelven los datos
    }
    
    public void contarVasos(){
        System.out.println("Hay "+listaVasos.size()+" disponibles");
    }
    
    
   
    
}


class HiloClientes extends Thread{
    Camarero camarero;
    vasoCerveza v;
    String nombreCliente;

    public HiloClientes(Camarero camarero, String nombreCliente) {
        this.camarero = camarero;
        this.nombreCliente = nombreCliente;
    }

    
    
    
    public synchronized void esperar(){
     
        try {
            Thread.sleep((int) (Math.random()*(1751)+250));
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    
}


public class Ua2ex1 {
    
    
    
    public static void main(String[] args) {
        Camarero camarero=new Camarero("Mou");
        
        
        
        
        HiloClientes cliente=new HiloClientes(camarero,"Homer");
        HiloClientes cliente2=new HiloClientes(camarero,"Barney");
        HiloClientes cliente3=new HiloClientes(camarero,"Carl");
        HiloClientes cliente4=new HiloClientes(camarero,"Lenny");
        HiloClientes cliente5=new HiloClientes(camarero,"Lurleen");
        
        
    }
    
}
