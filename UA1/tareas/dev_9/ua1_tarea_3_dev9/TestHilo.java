package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHilo {

    /*
    * Implementamos un test que no recibe argumentos
    * */
    @Test
    public void testHilo() {
        String [] args = {};
        Hilo hilo = new Hilo(args);

        Thread proceso = new Thread(hilo);

        proceso.start();

        try{

            proceso.join();

        } catch(InterruptedException e){
            e.printStackTrace();
        }

        Object contenidoHilo[] = hilo.probarTest();
        assertEquals(1,contenidoHilo[0]);

        System.out.println("El test ha funcionado correctamente");

    }
}
