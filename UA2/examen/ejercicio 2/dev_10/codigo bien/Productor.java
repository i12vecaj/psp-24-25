package org.example;

import java.util.Random;

public class Productor implements Runnable {
    private Buffer buffer;
    private Random numAleatorio;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
        this.numAleatorio = new Random();
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                char caracter = (char) (numAleatorio.nextInt(26) + 'A');
                System.out.println("Producido: " + caracter);
                buffer.producir(caracter);
                Thread.sleep(numAleatorio.nextInt(301) + 200);
            }
        } catch (InterruptedException error) {
            System.out.println("Productor interrumpido" + error);
        } finally {
            System.out.println("Productor finalizado");
        }
    }
}
