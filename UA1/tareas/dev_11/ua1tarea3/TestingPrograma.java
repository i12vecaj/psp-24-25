import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestingPrograma {

    @Test
    // Instancio un objeto del hilo del programa
    public void TestNoArgumentos() throws InterruptedException {
        ThreadPrograma programa = new ThreadPrograma(new String[]{}); // No paso argumentos como parametro
        Thread hilo = new Thread(programa);

        //Inicializo el programa
        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido.");
        }

        //Obtengo los datos del programa y hago el assert para hacer la comprobacion del exitCode y el mensaje recibido
        Object[] resultado = programa.getDatos();
        assertEquals("Esto indica que no hay argumentos.", resultado[0]);
        assertEquals(1, resultado[1]);
    }

    // Lo mismo para el resto de los tests pero con la diferencia de:

    @Test
    public void TestArgumentoCadena() throws InterruptedException {
        ThreadPrograma programa = new ThreadPrograma(new String[]{"hola"}); // Paso como parametro a los argumentos una cadena
        Thread hilo = new Thread(programa);

        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido.");
        }

        Object[] resultado = programa.getDatos();
        assertEquals("Esto indica que el argumento es una cadena.", resultado[0]);
        assertEquals(2, resultado[1]);
    }

    @Test
    public void TestArgumentoNumeroNegativo() throws InterruptedException {
        ThreadPrograma programa = new ThreadPrograma(new String[]{"-4"}); // Paso como parametro a los argumentos un numero negativo
        Thread hilo = new Thread(programa);

        hilo.start();

        try {
            hilo.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo se ha interrumpido.");
        }

        Object[] resultado = programa.getDatos();
        assertEquals("Esto indica que el argumento es un número menor a 0.", resultado[0]);
        assertEquals(3, resultado[1]);
    }

}
