package org.example;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

/*
* He creado una clase que va a testear las respuestas de el hilo 2 de modo que puedo ejecutar esto de
* forma independiente y verificar que el hilo 2 funciona. Lo he hecho en el mismo directorio pero también
* se puede hacer en la parte de test.
* */
public class Hilo2Test {
    @Test
    public void testHiloOpcion1() throws InterruptedException {
        String input = "1\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        hilo_2 hilo = new hilo_2();
        hilo.start();

        hilo.join(); //espero a que termine el hilo y luego compruebo la salida

        String output = out.toString();
        assertTrue(output.contains("Perfecto ejecutemos el siguiente hilo"));
    }

    @Test
    public void testHiloOpcion2() throws InterruptedException {
        String input = "2\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        hilo_2 hilo = new hilo_2();
        hilo.start();

        hilo.join(); //espero a que termine el hilo y luego compruebo la salida

        String output = out.toString();
        assertTrue(output.contains("Si pones * te saldrás"));
    }

    @Test
    public void testHiloOpcionIncorrecta() throws InterruptedException {
        String input = "3\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        hilo_2 hilo = new hilo_2();
        hilo.start();

        hilo.join(); //espero a que termine el hilo y luego compruebo la salida


        String output = out.toString();
        assertTrue(output.contains("Opción incorrecta. Seleccione un número entre 1 y 2.")); //aqui compruebo que la opcion es correcta
    }
}

