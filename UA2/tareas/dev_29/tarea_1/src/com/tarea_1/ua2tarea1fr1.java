package com.tarea_1;

public class ua2tarea1fr1 {
    public static void main(String[] args) {
        Contador c = new Contador(1);
        Thread h1 = new Thread(new HiloContador(c));
        Thread h2 = new Thread(new HiloContador(c));
        Thread h3 = new Thread(new HiloContador(c));
        Thread h4 = new Thread(new HiloContador(c));
        h1.start();
        h2.start();
        h3.start();
        h4.start();
    }
}
class Contador
{
    private int c = 0;
    Contador (int c) {
        this.c = c;
    }

    public void incrementa() {
        c += 100;
    }

    public void decrementa() {
        c--;
    }

    public int valor() {
        return c;
    }
}
class HiloContador implements Runnable {

    private final Contador contador;

    public HiloContador(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        synchronized (contador){
            for (int i = 0; i < 10000; i++) {
                contador.incrementa();
                System.out.println(Thread.currentThread().getName() + " " + contador.valor());
            }
        }

    }
}