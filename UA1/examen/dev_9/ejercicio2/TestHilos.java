package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHilos {
    /*
    * Implementamos un test que no recibe argumentos
    * */

    @Test
    public void testHiloTemperatura() {
        Temperatura temperatura = new Temperatura("Temperatura");

        //Creamos el hilo que extiende de Runnable
        Thread t = new Thread(temperatura);

        t.start();

        try{
            t.join();
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(temperatura.probarTest(),1);
    }




}
