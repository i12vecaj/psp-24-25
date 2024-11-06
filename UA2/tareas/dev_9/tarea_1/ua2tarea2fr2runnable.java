package org.example;

import java.util.ArrayList;

/**
 *HILO QUE USA SINCRONIZACION E IMPLEMENTA RUNNABLE
 */
class ua2tarea2fr2runnable {
    public static void main(String[] args) {
        Contador contador = new Contador();

        ArrayList<HiloIncrementador> list = new ArrayList<HiloIncrementador>();
        ArrayList<Thread> list_runnable = new ArrayList<Thread>();

        HiloIncrementador h1 = new HiloIncrementador("hilo 1",1000,contador);
        HiloIncrementador h2 = new HiloIncrementador("hilo 2",1000,contador);
        HiloIncrementador h3 = new HiloIncrementador("hilo 3",1000,contador);
        HiloIncrementador h4 = new HiloIncrementador("hilo 4",1000,contador);
        HiloIncrementador h5 = new HiloIncrementador("hilo 5",1000,contador);

        //creamos una instancia de thread que recibe el la instancia de la clase hilo
        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        Thread t3 = new Thread(h3);
        Thread t4 = new Thread(h4);
        Thread t5 = new Thread(h5);


        list_runnable.add(t1);
        list_runnable.add(t2);
        list_runnable.add(t3);
        list_runnable.add(t4);
        list_runnable.add(t5);

        for(Thread hilo: list_runnable) {
            Thread t = new Thread(hilo);
            t.start();
        }

        for(Thread hilo: list_runnable) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }


    public static class HiloIncrementador implements Runnable{

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

    public static class Contador {
        private int valor_contador;

        public Contador() {
            this.valor_contador = 0;
        }
        public synchronized void incrementarValorContador(int valor_a_incrementar) {
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