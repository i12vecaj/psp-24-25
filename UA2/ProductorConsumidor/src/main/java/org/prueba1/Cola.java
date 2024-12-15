package org.prueba1;
public class Cola {
    private int numero;
    private boolean disponible;

    public Cola() {
        disponible = false;
    }

    public synchronized int get() {
        while(!disponible) {
            try {
                wait(); //Espero mientras la cola este vacia
            }catch(InterruptedException e) {}
        }

        disponible = false; //Al obtener el numero, la cola se queda vacia
        notify(); //Notifico al productor que la cola esta vacia
        //System.out.println("Se consume: "+numero);
        return numero; //devuelvo el numero
    }

    public synchronized void put(int valor) {
        while(disponible) {
            try {
                wait(); //Espero mientras la cola este llena
            }catch(InterruptedException e) {}
        }

        numero = valor; //Coloco el numero en la cola
        disponible = true; //Al colocar el numero, la cola se queda llena
        //System.out.println("Se produce: "+numero);
        notify(); //Notifico al consumidor que la cola esta llena
    }
}