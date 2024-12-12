/**
Feedback JD: 12/12/2024
ENHORABUENA Alejandro

Fortalezas:

El código está bien organizado y es muy claro. Las clases Buffer, Productor y Consumidor están claramente separadas.
El uso de la sincronización con synchronized y wait()/notifyAll() es correcto y asegura que los hilos productores y consumidores se gestionen adecuadamente.
El uso de Thread.sleep() para simular el trabajo de cada hilo es adecuado y se genera un buen comportamiento aleatorio.
La implementación del método toString() en Buffer es muy útil para observar el estado del buffer y facilita el seguimiento del proceso.

Mejoras:
El uso de System.out.println() dentro de los métodos sincronizados es adecuado para la demostración, pero en un entorno real podría ser una fuente de congestión si los hilos se ejecutan a una velocidad alta. Considera reemplazarlo por un mecanismo de logging más eficiente si el sistema debe escalar.
Aunque el código usa join() para esperar a que los hilos terminen, en este caso, los hilos se ejecutan de manera infinita, lo que hace que esta espera nunca termine. Esto no es un problema en este contexto, pero es algo a tener en cuenta si se modifica el flujo del programa.
Documentación escasa, con el método que hemos indicado
**/

import java.util.Random;

public class ua2ex2 {

    //  esta la clase buffer entre productor y consumidor
    static class Buffer {
        private final char[] buffer; // lo hago para almacenar los caracter
        private final int capacidad;
        private int elementos = 0;
        private int indiceEscritura = 0;
        private int indiceLectura = 0;

        // este construtor lo hago para inciar el buffer
        public Buffer(int capacidad) {
            this.capacidad = capacidad;
            this.buffer = new char[capacidad];
        }

        // usamos la sincronizacion para poder agregar elementos
        public synchronized void producir(char c) throws InterruptedException { //espera ya que el buffer esta lleno
            while (elementos == capacidad) {
                System.out.println("el buffer esta lleno tiene que esperar.");
                wait(); // uso esto para que no se bloquee y notique
            }
            buffer[indiceEscritura] = c;
            indiceEscritura = (indiceEscritura + 1) % capacidad; // actualizo el indice
            elementos++; // incremento el contador de elementos
            System.out.println("se producio: " + c + " | el buffer esta asi en este moemento: " + this);
            notifyAll();
        }
        // este metodo lo utilizor para coger un elemento del buffer
        public synchronized char consumir() throws InterruptedException { // pongo una espera cada vez que el buffer este vacio
            while (elementos == 0) {
                System.out.println("el bufer esta vacio esperando...");
                wait();
            }
            char c = buffer[indiceLectura]; // se obtiene la letra
            indiceLectura = (indiceLectura + 1) % capacidad; // se actualiza el indice
            elementos--; // Decrementa el contador de elementos
            System.out.println(" se ha consumido: " + c + " | estado del buffer: " + this);
            notifyAll();
            return c;
    }

        //  metode que hace que el estado del buffer como una cadena
        @Override
        public synchronized String toString() {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < capacidad; i++) {
                if (i == indiceLectura && i == indiceEscritura && elementos == 0) {
                    sb.append("-");
                } else if (i == indiceLectura && elementos > 0) {
                    sb.append("l");
                } else if (i == indiceEscritura && elementos < capacidad) {
                    sb.append("e");
                } else {
                    sb.append(buffer[i]);
                }
            }
            return sb.append("]").toString();
        }
    }

    // clase hilo consumidor
    static class Consumidor extends Thread {
        private final Buffer buffer;
        private final Random random = new Random();

        // constructor que recibe el buffer como argumento
        public Consumidor(Buffer buffer) {
            this.buffer = buffer;
        }

        // Método ejecutado al iniciar el hilo
        @Override
        public void run() {
            try {
                while (true) { // Bucle infinito para consumir elementos continuamente
                    char c = buffer.consumir(); // Consume un carácter del buffer
                    Thread.sleep(random.nextInt(401) + 300); // Simula un tiempo de procesamiento
                }
            } catch (InterruptedException e) {
                System.err.println("error consumidor: " + e.getMessage());
            }
        }
    }

    // Clase hilo productor
    static class Productor extends Thread {
        private final Buffer buffer;
        private final Random random = new Random();

        // ocnstructor que recibe el buffer
        public Productor(Buffer buffer) {
            this.buffer = buffer;
        }


        @Override // metodo ejecutado al iniciar el hilo
        public void run() {
            try {
                while (true) {
                    char c = (char) (random.nextInt(26) + 'a');
                    buffer.producir(c);
                    Thread.sleep(random.nextInt(301) + 200);
                }
            } catch (InterruptedException e) {
                System.err.println("error en Productor: " + e.getMessage());
            }
        }
    }

    // este metodo inicia el programa
    public static void main(String[] args) {
        System.out.println("inicio del programa");

        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start(); // inicia el hilo productor
        consumidor.start(); // inicia el hilo consumidor

        try {
            productor.join();
            consumidor.join();
        } catch (InterruptedException e) {
            System.err.println("hay un error en el hilo principal: " + e.getMessage());
        }

        System.out.println("este programa se termina");
    }
}
