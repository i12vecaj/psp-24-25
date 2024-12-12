/**
Feedback JD: 12/12/2024
Buen trabajo Rafa. Algunas mejoras:
    Uso de la variable disponible: La lógica de esta variable es redundante y no se necesita con un índice de escritura (indiceEscritura) y lectura (indiceLectura) bien gestionados. Además, no refleja correctamente la condición de lleno o vacío del buffer circular.
    Buffer circular no implementado: Los índices (indiceEscritura y indiceLectura) deben operar en un esquema circular (% capacidad), de lo contrario, se desperdicia espacio en el buffer.
    Condición de espera indefinida: Aunque el buffer es limitado, el código no considera adecuadamente la condición en la que el índice de escritura alcanza la capacidad, lo que puede causar errores.
**/

import java.util.Random;
/**
 * @brief              Clase buffer usada por los productores y consumidores
 *
 *                      Esta clase se utiliza para la sincronizacion de los productores y consumidores y tiene
 *                      un buffer de tamanio capacidad, con sus indices de escritura y lectura
 *
 * @class              Class Buffer
 */
class Buffer{
    private char[] buffer;  /**< Buffer de tamanio capacidad */
    private  int capacidad;  /**< Capacidad del buffer */
    private int indiceEscritura;  /**< Indice de escritura */
    private int indiceLectura;  /**< Indice de lectura */
    private boolean disponible; /**< Indica si el buffer esta disponible */

    Buffer( int capacidad){
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
        this.indiceEscritura = 0;
        this.indiceLectura = 0;
        this.disponible = false;
    }
    /**
     *@brief              metodo encargado de producir
     *
     *                    metodo que comprueba la disponibilidad del buffer y si esta disponible le introduce la variable que se le pase
     *
     *@param              char c variable a introducir
     *
     *@return             void
     *@retval             no devuelve nada
     */
    public synchronized void producir(char c){
        while(disponible){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(indiceEscritura<capacidad){
            disponible = true;
            buffer[indiceEscritura] = c;
            indiceEscritura++;
            notify();
        }

    }
    /**
     *@brief              metodo encargado de consumir
     *
     *                    comprueba la disponibilidad del buffer y si esta disponible lo consume
     *
     *@param              no le entra nada
     *
     *@return             return char
     *@retval             devuelve el caracter que consume
     */
    public synchronized char consumir(){
        while(!disponible){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(indiceLectura<capacidad){
            indiceLectura++;
            disponible = false;
            notify();
        }
        return buffer[indiceLectura-1];
    }
}/**
 * @brief              Clase productor
 *
 *                     clase que se encarga de producir los caracteres en el buffer y lo hace de manera aleatoria, tambien duerme durante un tiempo aleatorio
 *
 * @class              Class productor
 */
class Productor extends Thread{
    private Buffer buffer;
    public Productor(Buffer buffer){
        this.buffer = buffer;
    }
    /**
     *@brief              metodo encargado de producir run
     *
     *                    metodo sobreescrito de la clase Thread
     *
     *@param              nada
     *
     *@return             void
     *@retval             no devuelve nada
     */
    public void run(){
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            int numeroSleep = random.nextInt(200)+300;
            Random random2 = new Random();
            char caracterRandom= (char)('A'+random2.nextInt(26));
            try {
                buffer.producir(caracterRandom);
                System.out.println("Producido: "+caracterRandom);
                sleep(numeroSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 * @brief              Clase consumidor
 *
 *                     clase que se encarga de consumir los caracteres del buffer y duerme durante un tiempo aleatorio
 *
 * @class              Class Name
 */
class Consumidor extends Thread{
    private Buffer buffer;
    public Consumidor(Buffer buffer){
        this.buffer = buffer;
    }
    /**
     *@brief              metodo encargado de consumir run
     *
     *                    metodo sobreescrito de la clase Thread
     *
     *@param              nada
     *
     *@return             void
     *@retval             no devuelve nada
     */
    public void run(){
        for (int i = 0; i < 10; i++) {
            char c = buffer.consumir();
            Random random = new Random();
            int numeroSleep = random.nextInt(400)+300;
            try {
                System.out.println("Consumido: "+c);
                sleep(numeroSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
/**
 * @brief              clase main 
 *
 *                     clase donde se instancian los productores y consumidores, a demas del buffer
 *
 * @class              Class Main
 */

class Main {
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        try {
            productor.start();
            consumidor.start();
            productor.join();
            consumidor.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Terminado");
    }
}
