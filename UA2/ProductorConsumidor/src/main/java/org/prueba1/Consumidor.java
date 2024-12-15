package org.prueba1;

public class Consumidor implements Runnable {
    Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;
    }
    @Override
    public void run() {
        while (true) {
            if(cola.get()>0)
                System.out.println("Consumido " + cola.get()+" ");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
