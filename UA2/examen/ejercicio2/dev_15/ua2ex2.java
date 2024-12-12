/**

Feedback JD: 12/12/2024

El resultado por pantalla no es el esperado: solo consume.

Valoro tu esfuerzo y solución, pero no es correcto tu planteamiento.

**/

import java.util.Random;

//Clase Buffer
public class ua2ex2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10); //Instanciamos Buffer con 10 elementos
        new Productor(buffer).start();      //Arrancamos el hilo
        new Consumidor(buffer).start();     //Arrancamos el hilo
    }
}

class Buffer {
    private char[] buffer;
    private int capacidad, escribir, leer, count;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        buffer = new char[capacidad];
    }

    public synchronized void producir(char c) {
        while (count == capacidad) waitThread();
        buffer[escribir] = c;
        escribir = (escribir + 1) % capacidad;
        count++;
        notifyAll();
    }

    public synchronized char consumir() {
        while (count == 0) waitThread();
        char c = buffer[leer];
        leer = (leer + 1) % capacidad;
        count--;
        notifyAll();
        return c;
    }

    private void waitThread() {
        try { wait(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

//Clase Productor
class Productor extends Thread {
    private Buffer buffer;

    public Productor(Buffer buffer) { this.buffer = buffer; }

    public void run() {
        Random random = new Random();
        while (true) {
            buffer.producir((char) (random.nextInt(26) + 'A'));
            sleepThread(200 + random.nextInt(300));
        }
    }
    private void sleepThread(int time) {
        try { Thread.sleep(time); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}

//Clase Consumidor
class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) { this.buffer = buffer; }

    public void run() {
        Random random = new Random();
        while (true) {
            System.out.println("Letra Consumida: " + buffer.consumir());
            sleepThread(300 + random.nextInt(400));
        }
    }
    private void sleepThread(int time) {
        try { Thread.sleep(time); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}
