/**
Feedback JD: 12/12/2024
Casi te das tu mismo el feedback de tu código, pero añado más cositas:

Fortalezas:
Buena implementación básica del patrón productor-consumidor.
Uso adecuado de synchronized y wait/notifyAll para la sincronización de los hilos.
La lógica del productor y consumidor está clara y funcional.

Mejoras:
El buffer debería ser más dinámico en cuanto al tamaño; el actual enfoque de producir y consumir 20 caracteres puede ser confuso o innecesario en algunos casos.
Se podrían agregar más verificaciones o control de excepciones, sobre todo al manipular la lista del buffer.
La lógica de producción y consumo está bien, pero se podría mejorar la gestión del tiempo de espera, y considerar más flexibilidad en la cantidad de elementos procesados por cada hilo.

**/

import java.util.ArrayList;

/* 
Me he dado cuenta que en mi solución no esta correcta debido a que el productor, produce la cantidad maxima
de caracteres para introducirlo en el buffer, avisa de que termino, y el consumidor empieza a consumir del buffer.
Para solucionar esto deberia implementar la logica de los metodos sincronizados producir y consumir de la clase
Buffer en los metodos run de las clases Productor y Consumidor, respectivamente.

todo -> implementar la logica en los metodos run de las clases Productor y Consumidor en lugar de en los metodos de la clase Buffer
*/

//Declaro los atributos del buffer compartido:
/**
 * @brief              Clase buffer
 *
 *                     Clase compartida por el productor y el consumidor
 *
 * @class              Buffer
 */
class Buffer {
    public ArrayList<Character> buffer;
    public int capacidad;

    public Buffer(int capacidad) {
        this.capacidad = capacidad; /**< Capacidad maxima de la lista, que se recoge por parametro*/
        this.buffer = new ArrayList<Character> (); /**< Buffer principal*/
    }

    //Controlo que el buffer no este lleno para producir
    /**
     *@brief             Metodo producir
     *
     *                   Metodo que va introduciendo caracteres en el buffer de forma sincronizada
     *
     *
     *@return             Vacío, va imprimiendo los caracteres que se van añadiendo
     */
    public synchronized void producir() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            if (buffer.size() == capacidad) {
                System.out.println("El buffer está lleno, espera...");
                wait();
            }
            buffer.add((char) (Math.random() * 26 + 'A'));
            System.out.println("Produjo:" + buffer);
            notifyAll();
            try {
                double tiempoAleatorio = (Math.random() * (500 - 200 + 1)) + 200;
                Thread.sleep((long) tiempoAleatorio);
            } catch (InterruptedException e) {}
        }
    }

    //Controlo que el buffer no este vacío para consumir
    /**
     *@brief             Metodo consumir
     *
     *                   Metodo que va consumiendo caracteres del buffer de forma sincronizada
     *
     *
     *@return             Vacío, va imprimiendo los caracteres que se van consumiendo
     */
    public synchronized void consumir() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            if (buffer.isEmpty()) {
                System.out.println("El buffer está vacío, espera...");
                wait();
            }
            System.out.println("Consumió: " + buffer.removeFirst());
            notifyAll();
            try {
                double tiempoAleatorio = (Math.random() * (700 - 300 + 1)) + 300;
                Thread.sleep((long) tiempoAleatorio);
            } catch (InterruptedException e) {}
        }
    }
}

/**
 * @brief              Clase Productor
 *
 *                     Clase hilo que va produciendo
 *
 * @class              Productor
 */
class Productor implements Runnable {
    public Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer; /**< Buffer compartido recogido por parametro*/
    }

    @Override
    public void run() {
        try {
            buffer.producir();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

/**
 * @brief              Clase Consumidor
 *
 *                     Clase hilo que va consumiendo
 *
 * @class              Consumidor
 */
class Consumidor implements Runnable {
    public Buffer buffer;

    public Consumidor(Buffer buffer) {
        this.buffer = buffer; /**< Buffer compartido recogido por parametro*/
    }

    @Override
    public void run() {
        try {
            buffer.consumir();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

//Declaro los hilos y el objeto Buffer e inicializo el productor y el consumidor
/**
 * @brief              Clase main
 *
 *                     Clase main donde se inicializan los hilos y se declara el objeto del buffer
 *
 * @class              ua2ex2
 */
public class ua2ex2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));

        productor.start();
        consumidor.start();
    }
}

