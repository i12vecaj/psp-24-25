package ua1ex1p2;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Testing {
  @Test
  public void testHiloTemperatura(){
    HiloTemperatura hiloTemperatura = new HiloTemperatura();
    Thread threadTemperatura = new Thread(hiloTemperatura);


    try{
      threadTemperatura.start();

      assertEquals(0,hiloTemperatura.exitCode);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testHiloHumedad(){

    HiloHumedad hiloHumedad = new HiloHumedad();
    Thread threadHumedad = new Thread(hiloHumedad);




    try{
      threadHumedad.start();


      assertEquals(0,hiloHumedad.exitCode);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testHiloEstado(){

    HiloEstadoPlantas hiloEstado = new HiloEstadoPlantas();
    Thread threadEstado = new Thread(hiloEstado);


    try{

      threadEstado.start();

      assertEquals(0,hiloEstado.exitCode);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
