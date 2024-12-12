/**
Puntos fuertes:
El código está bien estructurado y es fácil de entender.
El uso de sincronización (synchronized, wait(), notifyAll()) es correcto y asegura que no haya condiciones de carrera.
La lógica del buffer circular es adecuada y correctamente implementada.
La separación clara de responsabilidades en las clases Productor, Consumidor y Buffer hace que el código sea modular y fácil de mantener.

Áreas de mejora:
El uso de notifyAll() es innecesario y puede ser menos eficiente que notify() en este contexto, ya que solo hay un productor y un consumidor que necesitan ser notificados, no todos.
Los tiempos de espera de Thread.sleep() son fijos. Utilizar un rango aleatorio dentro de un intervalo haría que el comportamiento fuera más dinámico y realista, simulando mejor un entorno de producción/consumo variable.
Aunque se manejan correctamente las excepciones, sería recomendable mejorar el manejo de la interrupción de hilos, específicamente asegurándose de que Thread.currentThread().interrupt() se llame cuando sea necesario después de la captura de una excepción InterruptedException.

Conclusión: El código es sólido y cumple bien con la tarea. Sin embargo, hay algunos detalles que pueden mejorarse para optimizar el rendimiento y la simulación. La calificación refleja un buen trabajo, pero con espacio para algunos ajustes que aumenten la eficiencia y flexibilidad del código.
**/

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

