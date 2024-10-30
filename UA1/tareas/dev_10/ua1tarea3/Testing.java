package UA1.tareas.dev_10.ua1tarea3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Testing {
  @Test
  //Test para validar que no metan argumentos.
  public void validarArgsVacio() throws InterruptedException {
    hilo h1 = new hilo(new String[] {});
    Thread hilo = new Thread(h1);
    hilo.start();
    try {
      hilo.join();
    } catch (InterruptedException error) {
      error.printStackTrace();
    }
    // Estoy llamando al metodo que me pasa los datos de la variable exit
    h1.getExit();
    assertEquals(1,h1.exit);//AssertEquals comprueba l a igualdad entre los dos parametros que le paso en el parentesis.
  }
  @Test
  public void validarArgsCadena() throws InterruptedException {
    hilo h1 = new hilo(new String[] {"Hola bro"});
    Thread hilo = new Thread(h1);
    hilo.start();
    try {
      hilo.join();
    } catch (InterruptedException error) {
      error.printStackTrace();
    }
    h1.getExit();
    assertEquals(2,h1.exit);
  }

  @Test
  public void validarArgsNumeroEntero() throws InterruptedException {
    hilo h1 = new hilo(new String[] {"-1"});
    Thread hilo = new Thread(h1);
    hilo.start();
    try {
      hilo.join();
    } catch (InterruptedException error) {
      error.printStackTrace();
    }
    h1.getExit();
    assertEquals(3,h1.exit);
  }
}