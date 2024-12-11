/**
 * @file               ua2ex2.java
 *
 * @brief              Producer - Consumer model, to implement threads and synchronization.
 *
 *                     By using Producer - Consumer, we assure that information flows smoothly in
 *                     between classes. The producer sends the Buffer class information, and the consumer
 *                     receives the information waiting in the buffer. In this very case the implementation
 *                     was conceived in the form of a random char generator that is later stored in said buffer.
 */




import java.util.Random;
/**
 * @brief              stores the information given
 *
 *                     Producer will give the buffer a series of individual
 *                     characters. Buffer's job is to store these characters
 *                     and defining what Producing and Consuming is. introduces
 *                     char[], capacidad, indiceEscritura, indiceLectura and elementos.
 *                     will see later in code
 *
 * @class              Buffer
 */

class Buffer {
    private char[] buffer;
    private int capacidad;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;
    private int elementos = 0;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
    }

    /**
     *@brief              Produces character for buffer to store
     *
     *                    producir(char c) uses a while() loop that will equal elementos and capacidad.
     *                    if they happen to be so, buffer is defined full and will not be able to produce
     *                    any more characters. Hence the message "Buffer lleno, esperando para producir.."
     *
     *                    If Buffer is NOT empty, producir() will generate a char (c), which will add 1  element to
     *                    the elementos variable.
     *
     *@param              c
     *
     */

    public synchronized void producir(char c) {
        while (elementos == capacidad) {
            try {
                System.out.println("Buffer lleno, esperando para producir...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        buffer[indiceEscritura] = c;
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        elementos++;
        System.out.println("Producido: " + c + " | Elementos en buffer: " + elementos);
        notifyAll();
    }

    /**
     *@brief              gives away character for consumer to have
     *
     *                    much like the producir method, this one will do almost exactly
     *                    the same; only exception being that instead of adding an element
     *                    to the elementos variable, it will be decreased by 1.
     *
     *
     *@return             char c
     *@retval             because consumer needs the buffer to generate a char to
     *                    esentially consume it, this method will return said char.
     */

    public synchronized char consumir() {
        while (elementos == 0) {
            try {
                System.out.println("Buffer vacío, esperando para consumir...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        char c = buffer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % capacidad;
        elementos--;
        System.out.println("Consumido: " + c + " | Elementos en buffer: " + elementos);
        notifyAll(); //lets every thread know it's their turn
        return c;
    }
}


/**
 * @brief              will use buffer.producir(c) to generate random char
 *
 *                     Producer will make use of buffer.producir and generate a random character
 *                     with Math.random() method.
 *
 * @class              Productor
 */

class Productor extends Thread {
    private Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     *@brief              thread that generates random char
     *
     *                    Producer will make use of buffer.producir and generate a random character
     *                    with Math.random() method
     *
     *
     */
    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            char c = (char) (Math.random() * 26 + 'A');
            buffer.producir(c);
            try {
                Thread.sleep(rand.nextInt(301) + 200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

/**
 * @brief              consumes char from buffer
 *
 *                     makes use of buffer.consumir, specified up above in consumir() method
 *
 * @class              Consumidor
 */

class Consumidor extends Thread {
    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    /**
     *@brief              thread that consumes element
     *
     *                    Consumer will esentially remove element from elementos
     *                    variable. more up above in consumir() method
     *
     *
     */

    @Override
    public void run() {
        Random rand = new Random();
        while (true) {
            char c = buffer.consumir();
            try {
                Thread.sleep(rand.nextInt(401) + 300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

/**
 * @brief              runs the program
 *
 *                     will create threads and give value to capacidad, which is the
 *                     variable that stores the amount of char c that will fit in.
 *                     later in the code, threads are started.
 *
 * @class              Class Name
 */

public class ua2ex2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Productor hiloProductor = new Productor(buffer);
        Consumidor hiloConsumidor = new Consumidor(buffer);

        hiloProductor.start();
        hiloConsumidor.start();
    }
}
