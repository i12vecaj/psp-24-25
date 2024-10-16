import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestingHilos { //No me ha dado tiempo a implementarlo bien, espero que se entienda la idea.

    @Test
    void testComprobarTemperatura () {
        HiloTemperatura temperatura = new HiloTemperatura();
        Thread h1 = new Thread(temperatura);

        temperatura.getTemperatura();
        assertEquals("Temperatura obtenida.", temperatura.mensajeExito);
    }

    @Test
    void testComprobarHumedad () {
        HiloHumedad humedad = new HiloHumedad();
        Thread h2 = new Thread(humedad);

        humedad.getHumedad();
        assertEquals("Humedad obtenida.", humedad.mensajeExito);
    }

    @Test
    void testComprobarPlantas () {
        HiloPlantas plantas = new HiloPlantas();
        Thread h3 = new Thread(plantas);

        plantas.getPlantas();
        assertEquals("Plantas obtenidas.", plantas.mensajeExito);
    }
}
