package org.example;

import java.util.ArrayList;


/*
* HILO QUE NO USA SINCRONIZACION
* */
public class ua2tarea1fr1 {
    public static void main(String[] args) {

        //Instanciamos las clases

        Contador contador = new Contador();

        ArrayList<HiloIncrementador> list = new ArrayList<HiloIncrementador>();
        ArrayList<Thread> list_runnable = new ArrayList<Thread>();

        HiloIncrementador h1 = new HiloIncrementador("hilo 1",1000,contador);
        HiloIncrementador h2 = new HiloIncrementador("hilo 2",1000,contador);
        HiloIncrementador h3 = new HiloIncrementador("hilo 3",1000,contador);
        HiloIncrementador h4 = new HiloIncrementador("hilo 4",1000,contador);
        HiloIncrementador h5 = new HiloIncrementador("hilo 5",1000,contador);

        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        Thread t3 = new Thread(h3);
        Thread t4 = new Thread(h4);
        Thread t5 = new Thread(h5);


        //Añadimos las instancias a la lista
        list.add(h1);
        list.add(h2);
        list.add(h3);
        list.add(h4);
        list.add(h5);

        //Recorremos e iniciamos los hilos
        for(HiloIncrementador hilo: list) {
            hilo.start();
        }

        //Esperamos a que todos los hilos terminen
        for(HiloIncrementador hilo: list) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static class HiloIncrementador extends Thread{

        public String nombre;
        public Contador contador;
        public int valor_a_incrementar;

        public HiloIncrementador(String nombre, int valor_a_incrementar, Contador contador) {
            this.nombre = nombre;
            this.contador = contador;
            this.valor_a_incrementar = valor_a_incrementar;
        }
        @Override
        public void run() {
            contador.incrementarValorContador(valor_a_incrementar);
        }
    }
    //Clase contador que va a aumentar el valor de la variable va a suceder ya lo
    //digo una condicion de carrera

    public static class Contador {
        private int valor_contador;

        public Contador() {
            this.valor_contador = 0;
        }
        public void incrementarValorContador(int valor_a_incrementar) {
            System.out.println("El valor de el contador ahora es de " + valor_contador);
            valor_contador+=valor_a_incrementar;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notify();
        }
    }

}