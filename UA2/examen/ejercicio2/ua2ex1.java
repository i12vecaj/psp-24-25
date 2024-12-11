class Buffer {
    private final char[] buffer;
    private final int capacidad;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;
    private int elementosActuales = 0;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
    }


    public synchronized void producir(char c) throws InterruptedException {
        while (elementosActuales == capacidad) {
            wait();
        }


        buffer[indiceEscritura] = c;
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        elementosActuales++;
        notifyAll();
    }

    public synchronized char consumir() throws InterruptedException {
        while (elementosActuales == 0) {
            wait();
        }

        char c = buffer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % capacidad;
        elementosActuales--;
        notifyAll();
        return c;
    }
}

class Consumidor extends Thread {
    private final Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = buffer.consumir();
                System.out.println("Consumiendo: " + c);
                Thread.sleep(700);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Productor extends Thread {
    private final Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = (char) (Math.random() * 26 + 'A');
                System.out.println("Produciendo: " + c);
                buffer.producir(c);
                Thread.sleep( 500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class ua2ex1 {
    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();
    }
}

