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
