
import java.util.Random;
/*
 * @mainpage        ua2ex2
 * 
 * @file            ua2ex2.java
 * 
 * 
 * @brief           Examen de la asignatura servicios y proceso
 *                  El examen consta de utilizar el modelo Productor-Consumidor
 *                  para unos caracteres almacenados en array compartido
 * 
 * 
 * @class           Clase publica ua2ex2
 * 
 * @Funcion principal       
 */
public class ua2ex2{

public ua2ex2(int par) {
    }
 private char[] buffer;
 private int capacidad;
 private int indiceEscritura = 0;
 private int indiceLectura = 0;
 private int elementos = 0;   

/*
 * @class       Clase  Buffer
 * 
 */
public void Buffer(int capacidad) {/**<Esta clase VOID es la del Buffer */
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
    }
/*
 * @class   Clase producir
 * 
 */
    public synchronized void produccion(char c) throws InterruptedException {/**< Control de errores para la clase produccion*/
        while (elementos == capacidad) {
            wait(); 
        }
        buffer[indiceEscritura] = c;/**<C = indiceEscrituta para abreviar */
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        elementos++;
        System.out.println("Producido: " + c);
        notifyAll(); 
    }

    public synchronized char consumir() throws InterruptedException {
        while (elementos == 0) {
            wait(); 
        }
        char c = buffer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % capacidad;
        elementos--;
        System.out.println("Consumido: " + c);
        notifyAll(); 
        return c;
    }
/*
 * @class       Clase productor
 * 
 */
class Productor extends Thread {/**<Esta clase es la del Productor */
    private final ua2ex2 buffer;

    public Productor(ua2ex2 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            char c = (char) (random.nextInt(26) + 'A'); 
            try {
                buffer.produccion(c);
                Thread.sleep(random.nextInt(301) + 200); /**<Tiempo de espera random */
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumidor extends Thread {
    private ua2ex2 buffer;

    public Consumidor(ua2ex2 buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                char c = buffer.consumir();
                Thread.sleep(new Random().nextInt(400) + 300); 
            } catch (InterruptedException e) {
                Thread.currentThread();
            }
        }
    }
}

public class Main {
    public void main(String[] args) {
        ua2ex2 buffer = new ua2ex2(10); 
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        
        productor.start(); 
        consumidor.start();
    }
}

}








