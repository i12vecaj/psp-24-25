

public class Clasebuffer {
    private char[] buffer;
    private int capacidad;
    private int indiceEscritura;
    private int indiceLectura;
    private int size;


    public Clasebuffer(int capacidad) {
        this.buffer = new char[capacidad];
        this.capacidad = capacidad;
        this.indiceEscritura = 0;
        this.indiceLectura = 0;
        this.size = 0;
    }


    public synchronized void producir(char c) {
        while (size == capacidad) {
            try {
                System.out.println("Buffer lleno, productor espera...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        buffer[indiceEscritura] = c;
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        size++;
        System.out.println("Producido: " + c + " | Estado del buffer: " + this.toString());
        notifyAll();
    }

    public synchronized char consumir() {
        while (size == 0) {
            try {
                System.out.println("Buffer vacío, consumidor espera...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        char c = buffer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % capacidad;
        size--;
        System.out.println("Consumido: " + c + "  Estado del buffer: " + this.toString());
        notifyAll();
        return c;
    }


    @Override
    public String toString() {
        StringBuilder estado = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            estado.append(buffer[(indiceLectura + i) % capacidad]).append(" ");
        }
        return estado.toString().trim() + "]";
    }
}
