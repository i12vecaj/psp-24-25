import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class TestHilo {

    @Test
    public void testRun() throws Exception {
        Hilo h = new Hilo(); //Intanciamos el nuestra clase de Hilo
        h.start(); //Iniciamos el Hilo
        h.procesarEntrada("Hola mundo"); //Llamamos al método procesarEntrada que recibe un String
        h.procesarEntrada("Me llamo Francisco");
        h.procesarEntrada("10");
        h.procesarEntrada("*");
        List<String> lineas = h.getLineas(); //Obtenemos la líneas que ha guardado el hilo
        assertEquals(lineas.size(),3); //Comprobamos que el número de línas es igual al número de entradas que hemos indicamos
        assertEquals(lineas.get(0),("1 - Hola mundo")); //Comprobamos que las entradas que hemos indicado se han guardado correctamente
        assertEquals(lineas.get(1),("2 - Me llamo Francisco"));
        assertEquals(lineas.get(2),("3 - 10"));

    }

}
