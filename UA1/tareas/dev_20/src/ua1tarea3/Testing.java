package ua1tarea3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Testing {
  @Test
  public void testWithoutArgs(){
    String[] args = {};

    Hilo hiloInstancia = new Hilo();
    hiloInstancia.args = args;

    Thread hilo = new Thread(hiloInstancia);

    try{
      hilo.start();
      hilo.join();

      assertEquals(1, hiloInstancia.exitCode);
    }catch (Exception e){
      fail("ERROR: " + e);
    }
  }

  @Test
  public void testWhitStringArg(){
    String[] args = {"hola"};

    Hilo hiloInstancia = new Hilo();
    hiloInstancia.args = args;

    Thread hilo = new Thread(hiloInstancia);

    try{
      hilo.start();
      hilo.join();

      assertEquals(2, hiloInstancia.exitCode);
    }catch (Exception e){
      fail("ERROR: " + e);
    }
  }

  @Test
  public void testWhitInteger(){
    String[] args = {"1"};

    Hilo hiloInstancia = new Hilo();
    hiloInstancia.args = args;

    Thread hilo = new Thread(hiloInstancia);

    try{
      hilo.start();

      assertEquals(0, hiloInstancia.exitCode);
    }catch (Exception e){
      fail("ERROR: " + e);
    }
  }

  @Test
  public void testWhitNegativeInteger(){
    String[] args = {"-1"};

    Hilo hiloInstancia = new Hilo();
    hiloInstancia.args = args;

    Thread hilo = new Thread(hiloInstancia);

    try{
      hilo.start();
      hilo.join();

      assertEquals(3, hiloInstancia.exitCode);
    }catch (Exception e){
      fail("ERROR: " + e);
    }
  }
}
