/**
 * Antonio Gomez Cuevas
 * 
 * @file ua2ex2.java
 * @brief Implementacion de Examen de Programacion de Servicios y Procesos, Unidad 2
 */

 import java.util.Random;

 /**
  * @class ua2ex2
  * @brief Clase principal
  */
 public class ua2ex2 {
    /**
    * @brief Metodo principal que inicializa el buffer, productor y consumidor, y los ejecuta.
    */
     public static void main(String[] args) {
 
         Buffer buffer = new Buffer(10);
 
         Productor productor = new Productor(buffer);
         Consumidor consumidor = new Consumidor(buffer);
 
         productor.start();
         consumidor.start();
 
         try {
             productor.join();
             consumidor.join();
         } catch (InterruptedException e) {
             System.out.println("La Ejecucion principal ha sido interrumpida ");
         }
     }
 }
 
 /**
  * @class Buffer
  * @brief Clase que implementa el buffer compartido entre el productor y el consumidor.
  */
 class Buffer {
     private char[] buffer; ///< Array que almacena los caracteres
     private int capacidad; ///< Capacidad maxima del buffer
     private int indiceEscritura = 0; ///< Posicion actual de escritura en el buffer
     private int indiceLectura = 0; ///< Posicion actual de lectura en el buffer
     private int contador = 0; ///< Cantidad actual de elementos en el buffer
 
     /**
      * @brief Constructor que inicializa el buffer con la capacidad introducida
      * 
      * @param capacidad Tamaño maxiimo del buffer
      */
     public Buffer(int capacidad) {
         this.capacidad = capacidad;
         this.buffer = new char[capacidad];
     }
 
     /**
      * @brief Metodo sincronizado que produce caracteres y los añade al buffer
      * 
      * @param c Caracter a introducir
      * @throws InterruptedException Si el hilo es interrumpido
      */
     public synchronized void producir(char c) throws InterruptedException {
         while (contador == capacidad) {
             System.out.println("El Buffer esta lleno, productor espera");
             wait();
         }
 
         buffer[indiceEscritura] = c;
         indiceEscritura = (indiceEscritura + 1) % capacidad;
         contador++;
 
         System.out.println("Caracter producido: " + c );
 
         notifyAll();
     }
 
     /**
      * @brief Metodo sincronizado que consume un caractet del buffer
      * 
      * @return Caracter a consumir del buffer
      * @throws InterruptedException Si el hilo es interrumpido
      */
     public synchronized char consumir() throws InterruptedException {
         while (contador == 0) {
             System.out.println("El Buffer esta lleno, consumirdo espera");
             wait();
         }
 
         char c = buffer[indiceLectura];
         indiceLectura = (indiceLectura + 1) % capacidad;
         contador--;
 
         System.out.println("Caracter consumido: " + c );
 
         notifyAll();
         return c;
     }
 }
 
 /**
  * @class Productor
  * @brief Clase que representa un productor que genera caracteres aleatorios y los añade al buffer
  */
 class Productor extends Thread {
     private Buffer buffer; ///< Buffer compartido
     private Random random = new Random(); ///< Generador de numeros aleatorios
 
     /**
      * @brief Constructor que inicializa el productor con un buffer dado
      * 
      * @param buffer Buffer compartido
      */
     public Productor(Buffer buffer) {
         this.buffer = buffer;
     }
 
     /**
      * @brief Metodo que ejecuta la logica del productor
      */
     @Override
     public void run() {
         for (int i = 0; i < 10; i++) {
             try {
                 char c = (char) (random.nextInt(26) + 'A');
                 buffer.producir(c);
                 Thread.sleep(200 + random.nextInt(300));
             } catch (InterruptedException e) {
                 System.out.println("El hilo productor ha sido interrumpido" );
             }
         }  
     }
 }
 
 /**
  * @class Consumidor
  * @brief Clase que consume caracteres
  */
 class Consumidor extends Thread {
     private Buffer buffer; ///< Buffer compartido
     private Random random = new Random(); ///< Generador de numeros aleatorios
 
     /**
      * @brief Constructor que inicializa la clase con el buffer dado
      * 
      * @param buffer buffer compartido
      */
     public Consumidor(Buffer buffer) {
         this.buffer = buffer;
     }
 
     /**
      * @brief Metodo que ejecuta la logica del consumidor
      */
     @Override
     public void run() {
         for (int i = 0; i < 10; i++) {
             try {
                 char c = buffer.consumir();
                 System.out.println("Caracter procesado: " + c);
                 Thread.sleep(300 + random.nextInt(400));
             } catch (InterruptedException e) {
                 System.out.println("El hilo consumidor ha sido interrumpido");
             }
         }
         
     }
 }