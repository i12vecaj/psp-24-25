/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ua2tarea2fr1;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author daniel
 */

// Generamos la clase cuentacorriente

//probando git
class cuentacorriente
{
int saldo;


// agregaremos los bloques sincronizados de getsaldo, setsaldo y agregarsaldo

public cuentacorriente(int saldo)
        
{
    this.saldo = saldo;

}

// En getsaldo obtendremos los valores de manera aleatoria haciendo uso de la funcion ya existente Math.random
public synchronized int getsaldo()
{
try
{
Thread.sleep((int)(Math.random()*(1900)+250));
}catch(Exception e){}
return saldo;
}

// Aqui hacemos exactamente lo mismo que antes pero unicamente indicando el valor de saldo

public synchronized void setsaldo(int saldo)
{

try
{
Thread.sleep((int)(Math.random()*(1900)+250));
}catch(Exception e){}
this.saldo = saldo;
}

// En este caso creamos una nueva variable que sea anadir para sumar el nuevo valor al saldo

public synchronized  void agregarsaldo(int anadir, String nombre)
{

    System.out.println("La cuenta tenia un saldo de: "+this.saldo);
    this.saldo+=anadir;
    
    System.out.println("El nuevo saldo es: "+this.saldo);
    
    System.out.println("------------------------------");
    
    System.out.println("Autor de la operación: "+nombre);
}

}

// procedemos a crear la clase usuario


class usuario extends Thread
{

String nombre;
int saldo;
cuentacorriente cuenta;
public usuario(String nombre, int saldo, cuentacorriente cuenta )
{

this.nombre = nombre;
this.saldo=saldo;
this.cuenta = cuenta;
}
// Esta funcion nos permitirá la ejecución de los hilos
public void run()
{
cuenta.agregarsaldo(saldo, nombre);
}

}

public class Ua2tarea2fr1 {

    public static void main(String[] args) {
      
        
        // Declaramos los hilos y el valor inicial de la cuenta
        cuentacorriente cuenta = new cuentacorriente(150);
        ArrayList<Thread> hilos = new ArrayList<Thread>();
      for(int i=0; i<5; i++)
        {
            
         
            int saldo = (int) (Math.random()*(160)+50);
            usuario user = new usuario("Hilo "+i,saldo*i,cuenta);
            hilos.add(new Thread(user)); 
        }
        
      // Inicialización hilos
      
      for (int i=0; i<hilos.size();i++)
      {
      hilos.get(i).start();
      }
      
      // Finalización ejecución hilos
      
      for (int i=0; i<hilos.size();i++)
      {
      try
      {
      hilos.get(i).join();
      }catch(Exception e){}
      
          System.out.println("TOTAL: "+cuenta.getsaldo());
         
      }
      
      
        
    }
}
