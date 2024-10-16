import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class TestHilos {

    @Test
    public void testRun() throws Exception { //No me ha dado tiempo a implementar todo pero la idea es comprobar los valor que nos devuelven
        HiloHumedad humedad = new HiloHumedad();
        HiloTemperatura temperatura = new HiloTemperatura();
        boolean rango = 0 < temperatura.nuevaTemperatura() && temperatura.nuevaTemperatura() < 100;
        boolean rangohumedad = 0 < humedad.nuevaHumedad() && humedad.nuevaHumedad() < 100;


        assertEquals(true, rango);
        assertEquals(true, rangohumedad);

    }

}
