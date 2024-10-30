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

        assertEquals(temperatura.estado,1);

    }

    @Test
    public void testHiloHumedad() {
        Humedad humedad = new Humedad("Humedad");

        //Creamos el hilo que extiende de Runnable
        Thread h = new Thread(humedad);

        h.start();


        try{
            h.join();


        }catch (Exception e) {
            e.printStackTrace();
        }

        //Comprobar que lo básico lo cumple
        assertEquals(humedad.estado,1);

    }

    @Test
    public void testHiloEstadoPlantas() {
        EstadoPlantas estadoPlantas = new EstadoPlantas("Estado Plantas");

        //Creamos el hilo que extiende de Runnable
        Thread t = new Thread(estadoPlantas);

        t.start();


        try{
            t.join();


        }catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(estadoPlantas.estado,1);

    }


}
