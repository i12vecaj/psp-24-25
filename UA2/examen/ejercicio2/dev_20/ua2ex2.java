import java.util.Arrays;
import java.util.Random;

public class ua2ex2 {
  public static void main(String[] args) throws InterruptedException {
    Buffer buffer = new Buffer(10);
    // Creo el hilo productor
    for(int i = 0; i<10 ; i++) {
      Thread hiloProductor = new Thread(new Productor(buffer));
      hiloProductor.start();
      hiloProductor.join();
    }
    // Creo el hilo consumidor
    for(int i = 0; i<10 ; i++){
      Thread hiloConsumidor = new Thread(new Consumidor(buffer));
      hiloConsumidor.start();
      hiloConsumidor.join();
    }
  }
  /**
   * @brief              Es el objeto que es afectado por los demás teniendo un array
   *
   *
   *
   * @class              Buffer
   */
  public static class Buffer{
    Character[] buffer;
    int capacidad;
    int indiceEscritura;
    int indiceLectura;
    int contador;

    // En el constructor Instancio todos los atributos
    Buffer(int capacidad){
      this.capacidad = capacidad;
      buffer = new Character[capacidad];
      indiceEscritura = 0;
      indiceLectura = 0;
      contador = 0;
    }

    public synchronized void escribir(char c) throws InterruptedException {
      // Espero en el caso de que no haya más espacio
      while(contador > capacidad){
        wait();
      }
      // Lo notifico, agrego el caracter nuevo y calculo el siguiente índice
      System.out.printf("Agrego el caracter = " + c + "\n");
      buffer[indiceEscritura] = c;
      // El indice de escritura siempre suma, se resta en el método leer
      indiceEscritura ++;
      // utilizo el contador para calcular los caracteres 
      contador ++;

    }
    public synchronized void leer() throws InterruptedException {
      while (contador < 0){
        wait();
      }

      contador --;
      System.out.printf("El caracter eliminado es: " + buffer[indiceLectura] + "\n");
      // Elimino el caracter cambiandolo a null
      buffer[indiceLectura] = null;
      // El indice de lectura será el indice de escritura - 1 de esta manera eliminará el último caracter
      indiceLectura = indiceEscritura-1;
      // Resto el indice de Escritura
      indiceEscritura --;

    }

    @Override
    public String toString() {
      return "Buffer{" +
              "buffer=" + Arrays.toString(buffer) +
              '}';
    }
  }
  /**
   * @brief              Ingresa caracteres en el buffer
   *
   *
   *
   * @class              Productor
   */
  public static class Productor implements Runnable{
    Buffer buffer;
    Random random = new Random();
    Productor(Buffer buffer){
      this.buffer = buffer;
    }

    @Override
    public void run() {
      char caracter =  (char) (Math.random() * 26 + 'A');
      try {
        buffer.escribir(caracter);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      try {
        Thread.sleep(random.nextInt(200,500));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.printf("Estado del buffer: " + buffer.toString() + "\n");
    }
  }
  /**
   * @brief              Consume el buffer leyendo un caracter
   *
   *
   *
   * @class              Consumidor
   */
  public static class Consumidor implements Runnable{
    Buffer buffer;
    Random random = new Random();
    Consumidor(Buffer buffer){
      this.buffer = buffer;
    }
    @Override
    public void run() {
      try {
        buffer.leer();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      try {
        Thread.sleep(random.nextInt(300,700));
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.printf("Estado del buffer: " + buffer.toString() + "\n");

    }
  }


}
