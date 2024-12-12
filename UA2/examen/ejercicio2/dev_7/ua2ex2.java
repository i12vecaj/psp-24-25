/**
Feedback JD - 12//12/2024

Puntos fuertes:

Coordinación entre hilos bien implementada con synchronized, wait() y notifyAll().
Separación adecuada de responsabilidades entre las clases.

Aspectos a mejorar:
Ineficiencia en el consumo: no elimina elementos del buffer tras consumirlos, lo que podría generar problemas en escenarios más complejos.
Problemas de estilo: nombres de variables y clases poco descriptivos.
**/

package org.example;

import java.util.ArrayList;


/**
 * @brief Clase principal
 * <p>
 *        Esta clase es la que crea tanto el buffer(Recurso compartido), como los hilos
 *        Productor y Consumidor
 *
 * @class ua2ex2
 */
public class ua2ex2 {

    /**
     * @brief Método principal
     * <p>
     *        Este método crea los hilos Productor y Consumidor, pasandole como parámetro el recurso
     *        Buffer que tambien lo crea, y los inicia con el método start()
     */
    public static void main(String[] args) {
        Buffer buffer = new Buffer(10);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        System.out.println("=== Programa iniciado ===");
        productor.start();
        consumidor.start();


    }
}


/**
 * @brief Clase que simula un buffer compartido
 * <p>
 *        Esta clase simula un buffer que puede ser utilizado por dos hilos Productor y Consumidor
 */
class Buffer {
    public ArrayList<String>buffer; /**< Es el ArrayList donde guardamos los caracteres generados, he tenido que ponerlo de tipo String porqye me daba error al poner el tipo char*/
    public int capacidad; /**< Es el número máximo de caracteres que vamos a introducir en el buffer */
    public int indiceEscritura; /**< Esta variable nos indica la posicion por la que vamos escribiendo, en mi caso no la he utilziado ya que no la he necesitado porque con el método .add() del ArrayList me lo añadia en el último lugar automaticamente  */
    public int indiceLectura; /**< Lo utilizamos para saber la posición en la que el consumir está leyendo el buffer */

    /**
     * @brief Es el constructor de la clase Buffer.
     * Inicializa los atributos de la clase.
     *
     * @param capacidad
     */

    public Buffer(int capacidad){
        this.buffer = new ArrayList<String>();
        this.capacidad = capacidad;
        this.indiceEscritura = 0;
        this.indiceLectura = 0;
    }

    /**
     * @brief Método que simula la tarea de producir caracteres y los introduce en el buffer
     * <p>
     *        Este método se ejecuta en un hilo separado y produce caracteres al azar, los introduce en el buffer
     *        miesntra que este no esté lleno, en tal caso mandamos a dormir al hilo que ejecuta este metodo.
     *
     * @param c
     * @throws InterruptedException
     */
    public synchronized void producir(String c) throws InterruptedException {
        while(buffer.size() == capacidad){
            System.out.println("El productor esta esperando a que haya sitio para producir");
            wait();
        }
        System.out.println("Productor ha producido " + c);
        buffer.add(c);
        notifyAll();
    }

    /**
     * @brief Metodo que simula la tarea de consumir los caracteres
     * <p>
     *        Este método se ejcuta en un hilo separado y lo que hace es mostrar el caracter que toque leer por pantalla
     *
     * @throws InterruptedException
     */
    public synchronized void consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("El consumidor esta esperando a que haya elementos");
            wait();
        }
        if (indiceLectura < buffer.size()){
            String c = buffer.get(indiceLectura);
            indiceLectura++;
            System.out.println("El consumidor ha consumido: " + c );
        }

    }
}

/**
 * @brief Clase que simula el Productor
 * <p>
 *        Esta clase simula la tarea de producir caracteres y los introduce en el buffer
 */
class Productor extends Thread{
    public Buffer buffer; /**< Recurso compartido */


    /**
     * @brief Constructor
     * Recibe un buffer y lo asigna al atributo
     *
     * @param buffer
     */
    public Productor(Buffer buffer){
        this.buffer = buffer;
    }

    /**
     * @brief Sobrescribe el metodo run del hilo
     *<p>
     *        Definimos el comportamiento que va a tener nuestro hilo dentro de esta función
     */
    @Override
    public void run(){
        while (buffer.buffer.size() != buffer.capacidad){
            String c = String.valueOf((char) (Math.random() * 26 + 'A'));
            try {
                buffer.producir(c);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                int tiempo = (int) (Math.random()*(500 - 200 + 1)+200 );
                Thread.sleep(tiempo);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

}
/**
 * @brief Clase que simula el Consumidor
 * <p>
 *        Esta clase simula la tarea de consumir caracteres del buffer
 */
class Consumidor extends Thread{
    public Buffer buffer;  /**< Recurso compartido */
    /**
     * @brief Constructor
     * Recibe un buffer y lo asigna al atributo
     *
     * @param buffer
     */
    public Consumidor(Buffer buffer){
        this.buffer = buffer;
    }
    /**
     * @brief Sobrescribe el metodo run del hilo
     *<p>
     *        Definimos el comportamiento que va a tener nuestro hilo dentro de esta función
     */
    @Override
    public void run(){
        while (buffer.capacidad != buffer.indiceLectura+1){
            try {
                buffer.consumir();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                int tiempo = (int) (Math.random()*(700 - 300 + 1)+300 );
                Thread.sleep(tiempo);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Letras producidas : " +  buffer.buffer.toString() );
        System.out.println("=== Programa finalizado ===");

    }
}
