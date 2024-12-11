import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(10);
        Productor hProductor = new Productor(buffer);
        Consumidor hConsumidor = new Consumidor(buffer);

        hProductor.start();
        hConsumidor.start();

        hProductor.join();
        hConsumidor.join();

        /*Los sleep funcionan lo que pasa que los milisegundos que se nos pide hace que sean intervalos muy cortos
        He hecho que el bucle sea infinito para que así se pueda apreciar que funciona
        Al durar más da tiempo a que se desconpense por la diferencia de milisegundos y salta el aviso de que el buffer esta lleno.*/
    }
}

class Buffer {
    private ArrayList<Character> buffer;
    private int capacidad;
    private int indiceEscritura;
    private int indiceLectura;

    Buffer (int capacidad) {
        this.capacidad = capacidad;
        indiceEscritura = 0;
        indiceLectura = 0;

        buffer = new ArrayList<>();
    }

    public synchronized void producir(char c) throws InterruptedException {
        if (indiceEscritura < capacidad) {
            buffer.add(c);
            System.out.println("Se ha añadido un caracter: " + c);
            notifyAll();

            indiceEscritura++;
            indiceLectura--;
        } else {
            System.out.println("El buffer esta lleno ... Esperando");
            wait();
        }
    }

    public synchronized void consumir() throws InterruptedException {
        char c;

        if (indiceEscritura > 0) {
            c = buffer.getLast();
            buffer.removeLast();

            indiceEscritura--;
            indiceLectura++;

            System.out.println("Se ha consumido un caracter: " + c);
            notifyAll();
        } else {
            System.out.println("No hay ningun caracter ... Esperando");
            wait();
        }
    }
}

class Productor extends Thread {
    private Buffer buffer;

    Productor (Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.producir((char) (Math.random() * 26 + 'A'));
                sleep((long) ((Math.random()*300) + 200));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumidor extends Thread {
    private Buffer buffer;

    Consumidor (Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                buffer.consumir();
                sleep((long) (Math.random()*400) + 300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}