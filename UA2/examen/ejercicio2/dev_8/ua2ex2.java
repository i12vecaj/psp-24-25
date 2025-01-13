/**
Feedback JD: 12/12/2024

Funcionalidad básica cumplida: Todas las funcionalidades requeridas (FR1 a FR4) están implementadas y se entienden claramente.
Buen uso de sincronización: La implementación de los métodos wait() y notifyAll() asegura la sincronización de los hilos de manera eficiente.
Código estructurado y comentado: Los comentarios son claros y explican el propósito de cada parte del código.
Buffer circular: La lógica de índices cíclicos para gestionar el buffer es precisa y funcional.
Simulación controlada: La generación de caracteres aleatorios y los intervalos de tiempo para los hilos son coherentes y reproducen bien el escenario planteado.

Aspectos mejorables:
Uso de `while (true): Aunque funcional, sería recomendable manejarlo con condiciones más claras o señales de parada para evitar problemas en producción.
Manejo de excepciones: Aunque se utiliza interrupt(), sería ideal profundizar en el tratamiento de interrupciones para garantizar una terminación limpia.
Random como atributo estático: Podría optimizarse al declararlo como un atributo compartido para evitar múltiples instancias.

En general, el ejercicio está bien ejecutado, con una correcta implementación técnica y claridad en los objetivos alcanzados. Enhorabuena.

**/

import java.util.Random;

/*
    FR1 [2 puntos]. Clase Buffer
    clase buffer con sus atributos, constructor y metodos
    producir y consumir sincronizados para que no se pisen los hilos unos a otros
 */
class Buffer {
    private char[] buffer;
    private int capacidad;
    private int indiceEscritura = 0;
    private int indiceLectura = 0;
    private int contador = 0;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
    }

    public synchronized void producir(char caracter) throws InterruptedException {
        while (contador == capacidad) {
            wait();
        }
        buffer[indiceEscritura] = caracter;
        //le asigno el siguiente indice de escritura y si llega al final del buffer vuelve al principio con el modulo de la capacidad del buffer asi consigo recorrer todo el buffer
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        contador++;

        //System.out.println("Producido: " + caracter);

        //mustra por pantalla el indice de escritura y el caracter producido
        System.out.println(indiceEscritura+ "  Producido: " + caracter);

        notifyAll();
    }

    public synchronized char consumir() throws InterruptedException {
        while (contador == 0) {
            wait();
        }
        char caracter = buffer[indiceLectura];
        //le asigno el siguiente indice de lectura y si llega al final del buffer vuelve al principio con el modulo de la capacidad del buffer asi consigo recorrer el buffer
        indiceLectura = (indiceLectura + 1) % capacidad;
        contador--;

        //System.out.println("Consumido: " + caracter);

        //mustra por pantalla el indice de lectura y el caracter consumido
        System.out.println(indiceLectura+ "  Consumido: " + caracter);

        notifyAll();
        return caracter;
    }
}


/*
    FR2 [2 puntos]. Clase Productor
    clase productor que extiende de Thread
    constructor que recibe un buffer
    metodo run que produce un caracter aleatorio y lo envia al buffer entre 200 y 500 ms de espera
*/
class Productor extends Thread {
    private Buffer buffer;
    private Random random = new Random();

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            // Enrique me dijo que no era recomendable usar while true pero no se me ocurrio otra forma de que el bucle este en constante ejecucion
            while (true) {
                char caracter = (char) (random.nextInt(26) + 'A');
                buffer.producir(caracter);
                Thread.sleep(random.nextInt(300) + 200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


/*
    FR3 [2 puntos]. Clase Consumidor
    clase consumidor que extiende de Thread
    constructor que recibe un buffer
    metodo run que consume un caracter del buffer entre 300 y 400 ms de espera
*/
class Consumidor extends Thread {
    private Buffer buffer;
    private Random random = new Random();


    public Consumidor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            // x2 lo mismo que en la clase Productor
            while (true) {
                char caracter = buffer.consumir();
                Thread.sleep(random.nextInt(400) + 300);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


/*
    FR4 [2 puntos]. Clase ua2ex2
    clase main que crea un buffer, un productor y un consumidor
    inicia los hilos productor y consumidor
    y se van ejecutando de forma sincronizada
 */
public class ua2ex2 {
    public static void main(String[] args) {

        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        productor.start();
        consumidor.start();
    }
}
