package org.example;

import java.util.Random;

public class Consumidor implements Runnable {
    private Buffer buffer;
    private Random numAleatorio;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
        this.numAleatorio = new Random();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                char caracter = buffer.consumir();
                System.out.println("Procesando: " + caracter);
                Thread.sleep(numAleatorio.nextInt(401) + 300);
            }
        } catch (InterruptedException e) {
            System.out.println("Consumidor interrumpido");
        } finally {
            System.out.println("Consumidor finalizado");
        }
    }
}
