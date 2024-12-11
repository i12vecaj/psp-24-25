/**
 * @file               ua2ex2.java
 *
 * @brief              Aplicacion q produce y consume caracteres almacenados en un array
 *
 *                     Esta aplicacion alamacenará un array de los caracteres producidos por la clase Productor
 *                     en otra clase Buffer, y la clase Consumidor, se dedicará a extraerlos y procesarlos.
 */




import java.util.Random;

/**
 * @brief              Almacena el array de caracteres
 *
 *                     Recibe el caracter producido por la clase Productor y lo almacena,
 *                     como máximo almacenará 10 caracteres, ya que asi se ha establecido en
 *                     la Aplicacion Principal (ua2ex2).
 *
 * @class              Buffer
 */

class Buffer {

    private char[] buffer;
    private int capacidad;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;
    private int contador = 0;

    /**
     * @brief          El constructor sirve para inicializar el buffer con una capacidad (la cual se especifica en ua2ex2).
     *
     * @param          capacidad es el tamaño máximo del buffer (mas adelante se verá q es 10)
     */

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
    }

    /**
     * @brief          Añade un caracter al buffer si hay espacio disponible y espera si este está lleno.
     *
     * @param          c es la variable que se añadirá al buffer
     *
     */

    public synchronized void producir(char c) throws InterruptedException {
        while (!(contador == capacidad)) {
            buffer[indiceEscritura] = c;
            indiceEscritura = (indiceEscritura + 1) % capacidad;
            contador++;
            System.out.println(indiceEscritura + ". Se ha añadido un caracter: " + c + " y la capacidad actual de la cola está en: " + contador + "/" + capacidad );
            notifyAll();
        }
            wait();
    }

    /**
     * @brief          Extrae un carácter del buffer si hay elementos disponibles  y espera si este está lleno.
     *
     * @return         Devuelve el caracter extraído del buffer
     */

    public synchronized char consumir() throws InterruptedException {
        char c = buffer[indiceLectura];
        while (!(contador == 0)) {
            indiceLectura = (indiceLectura + 1) % capacidad;
            contador--;
            System.out.println(indiceLectura + ". Se ha eliminado un caracter: " + c + " y la capacidad actual de la cola está en: " + contador + "/" + capacidad );
            notifyAll();

        }
        wait();

        return c;
    }
}

/**
 * @brief              Genera caracteres aleatorios
 *
 *                     La clase Productor simula un hilo que genera caracteres aleatorios desde la A-Z y los almacena en el buffer.
 *
 * @class              Productor
 */


class Productor extends Thread {

    private Buffer buffer;
    private Random random = new Random();

    /**
     * @brief          El constructor sirve para inicializar el Productor haciendo referencia al Buffer compartido.
     *
     * @param          buffer que es el objeto buffer compartido anterior
     */

    public Productor (Buffer buffer){
        this.buffer = buffer;

    }

    /**
     * @brief         El método run genera caracteres aleatorios y los añade al buffer y espera un tiempo aleatorio entre 200 ms y 500 ms.
     *
     */
    
    @Override
    public void run(){
        try {
            char c = (char) (Math.random()*26+'A');
            buffer.producir(c);
            Thread.sleep(random.nextInt(200)+300);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}

class Consumidor extends Thread{

    private Buffer buffer;
    private Random random = new Random();

    public Consumidor(Buffer buffer) {
        this.buffer = buffer;

    }

    @Override
    public void run(){
        try {
            buffer.consumir();
            Thread.sleep(random.nextInt(300)+400);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}


public class ua2ex2 {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();

    }
}
