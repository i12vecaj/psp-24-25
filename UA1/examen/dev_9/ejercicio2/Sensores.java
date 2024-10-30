package org.example;

public class Sensores implements Runnable{
    Temperatura t;

    public Sensores() {

    }
    @Override
    public void run() {
        Temperatura temperatura = new Temperatura("Temperatura");
        Humedad humedad = new Humedad("Humedad");
        EstadoPlantas plantas = new EstadoPlantas("Estado");

        Thread t = new Thread(temperatura);
        Thread h = new Thread(humedad);
        Thread p = new Thread(plantas);

        t.start();
        h.start();
        p.start();

        try {
            t.join();
            h.join();
            p.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
