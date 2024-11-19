package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class Testing {
    /*
    * Realizamos un test de comprobacion de que la asignacion de valores desde
    * los hilos a la clase cuentaCorriente es correcta
    * */
    @Test
    public void testeoAsignacionDatos() {
        CuentaCorriente cuentaCorriente = new CuentaCorriente();

        Comprador comprador = new Comprador("Juan",cuentaCorriente,1000.0f);
        comprador.start();


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*
        * Hacemos la comprobacion de que el constructor asigna correctamente
        * */

        assertTrue(cuentaCorriente.getContent());

    }

    /*
    * Realizamos un test de comprobacion de que cuanto se inicia la cuenta
    * el saldo es de 0.0f
    * */
    @Test
    public void inicializacionCorrecta() {
        CuentaCorriente cuentaCorriente = new CuentaCorriente();
        assertFalse(cuentaCorriente.getContent());
    }
}
