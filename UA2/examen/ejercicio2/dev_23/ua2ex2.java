/**
Feedback JD: 12/12/2024
Enhorabuena Javier

Fortalezas:
Estructura clara y modular: El código está bien organizado en clases diferenciadas (Productor, Consumidor, Buffer) con responsabilidades claras.
Uso correcto de hilos y sincronización: El uso de synchronized, wait() y notify() en la clase Buffer está bien implementado para gestionar la interacción entre el productor y el consumidor, evitando condiciones de carrera.
Uso de Thread.currentThread().interrupt(): El manejo de interrupciones en los hilos es adecuado, permitiendo que el hilo se pueda interrumpir correctamente en caso de excepciones.
Lógica de producción y consumo: La lógica de producción de caracteres aleatorios y consumo con tiempos aleatorios está bien estructurada, haciendo que el comportamiento sea realista.
Implementación eficiente del buffer circular: El uso de índices circulares (% capacidad) para manejar el buffer es correcto, asegurando que las operaciones de lectura y escritura no se desborden.

Mejoras:
Mensajes de consola: Aunque los mensajes de consola son útiles, podrían ser más descriptivos para el usuario que observe el comportamiento. Por ejemplo, el consumidor podría imprimir también el número total de caracteres consumidos hasta el momento, o el productor el número de caracteres producidos.
Uso de Thread.sleep: Los tiempos de sueño para los hilos productor y consumidor están bien, pero podrías también considerar ajustar la variabilidad de los tiempos para simular de forma más realista los tiempos de producción y consumo en escenarios más complejos.
Lógica de interrupciones: El código maneja adecuadamente las excepciones InterruptedException, pero en algunos casos, sería más robusto restablecer el estado de interrupción del hilo con Thread.currentThread().interrupt() para que el hilo pueda manejar la interrupción de manera más controlada si se requiere.
**/

import java.util.Random;

public class ua2ex2 {
  public static void main(String[] args) {
    Buffer buffer = new Buffer(10);

    Thread hiloProductor = new Thread(new Productor(buffer));
    Thread hiloConsumidor = new Thread(new Consumidor(buffer));

    hiloProductor.start();
    hiloConsumidor.start();
  }
}

/**Clase que almacena la lista de caractener que son producidos y consumidos
 *
 * @class              Buffer
 */
class Buffer {
  private final char[] buffer;/**< Lista de caracteres*/
  private final int capacidad;/**< capacidad de la lista*/
  private int indiceEscritura = 0;/**< indice en el que se va a escribir*/
  private int indiceLectura = 0;/**< indice que se va a leer*/
  private int contador = 0;/**< contador de indice*/


  public Buffer(int capacidad) {
    this.capacidad = capacidad;
    this.buffer = new char[capacidad];
  }

  /**
   *                    Metodo que recive una letra y la introduce en la lista del buffer
   *
   *@param              letra Caracter a producir
   */
  public synchronized void producir(char letra) {
    while (contador == capacidad) {
      try {
        System.out.println("Buffer lleno. El productor espera...");
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    buffer[indiceEscritura] = letra;
    System.out.println("El productor ha producido la letra: " + letra);
    indiceEscritura = (indiceEscritura + 1) % capacidad; // Esta operacion hace que el indice siempre sea circular, es decir, si la capacidad es de 10 al llegar a 10 vuelve a 0
    contador++;
    notify();
  }

  /**
   *                    Metodo que devuelve una letra del buffer 
   *
   *@retval             devuelve la letra la cual se va a consumer
   */
  public synchronized char consumir() {
    while (contador == 0) {
      try {
        System.out.println("Buffer vacío. El consumidor espera...");
        wait();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    char letra = buffer[indiceLectura];/**< Letra que se va a consumir*/
    indiceLectura = (indiceLectura + 1) % capacidad; // Esta operacion hace que el indice siempre sea circular, es decir, si la capacidad es de 10 al llegar a 10 vuelve a 0
    contador--;
    notify();
    return letra;
  }
}


/**Clase que produce caracteres para asignarlos al buffer comun
 *
 * @class              Productor
 */
class Productor implements Runnable {
  private final Buffer buffer;/**< objeto de la clase Buffer*/
  private final Random random = new Random();

  public Productor(Buffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    for (int i = 0; i < 30; i++) {
      char letra = (char) (Math.random() * 26 + 'A');

      buffer.producir(letra);
      try {
        Thread.sleep(random.nextInt(200)+300);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}

/**Clase que consume caracteres del buffer comun
 *
 * @class              Consumidor
 */
class Consumidor implements Runnable {
  private final Buffer buffer;/**< objeto de la clase Buffer*/
  private final Random random = new Random();

  public Consumidor(Buffer buffer) {
    this.buffer = buffer;
  }

  @Override
  public void run() {
    for (int i = 0; i < 30; i++) {
      char letra = buffer.consumir();/**< Caracter que se va a consumir*/
      System.out.println("Se ha consumido la letra: "+letra);
      try {
        Thread.sleep(random.nextInt(300)+400);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }
}
