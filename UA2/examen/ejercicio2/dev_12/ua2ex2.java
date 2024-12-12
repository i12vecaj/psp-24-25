/**
Feedback JD: 12/12/2024
ENHORABUENA ANTONIO

Fortalezas:

El código está bien estructurado, con clases claramente diferenciadas y comentarios que explican cada parte de la implementación.
La lógica de los hilos (Productor y Consumidor) está correctamente manejada mediante el uso de synchronized, wait(), y notifyAll(), lo que asegura que los hilos no se bloqueen ni accedan al buffer de manera concurrente.
El uso del generador aleatorio Random para producir y consumir caracteres aleatorios está bien implementado, lo que simula de forma adecuada el comportamiento aleatorio del productor y consumidor.
Los mensajes de depuración en cada operación son claros y ayudan a seguir el flujo de ejecución del programa.

Mejoras:
Mensajes de consola: Los mensajes de consola para los hilos productor y consumidor tienen un pequeño error en el mensaje de espera: el productor espera cuando el buffer está lleno y el consumidor espera cuando el buffer está vacío. Los mensajes para cada caso deberían ser más precisos. Por ejemplo, el consumidor está esperando cuando el buffer está vacío, no "lleno", y viceversa para el productor.
Finalización del ciclo: Aunque el código está diseñado para funcionar indefinidamente, el bucle en los métodos run() de las clases Productor y Consumidor solo se ejecutan 10 veces. Sería interesante manejar un ciclo de vida más flexible, como hacerlo indefinido o añadir una condición para la finalización del proceso que se pueda controlar.
Manejo de interrupciones: Aunque se gestionan correctamente las interrupciones con try-catch en ambos hilos, el tratamiento podría mejorarse, por ejemplo, restableciendo el estado de interrupción del hilo utilizando Thread.currentThread().interrupt() dentro del bloque catch. Esto podría ser útil para una mayor robustez, especialmente si en algún momento el programa requiere un manejo de hilos interrumpidos más detallado.
**/


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
