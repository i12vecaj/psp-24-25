package org.example;

public class Comprador extends Thread{

    public float saldo_cuenta;

    public CuentaCorriente cuentaCorriente;

    public Comprador(String nombre, CuentaCorriente cuentaCorriente, float saldo_cuenta){
        /*
         * Se le asigna un nombre a el hilo mediante el metodo interno de el hilo setName()
         * el valor de este nombre lo sacamos mediante Thread.currentThread().getName()
         */
        setName(nombre);
        this.cuentaCorriente = cuentaCorriente;
        this.saldo_cuenta = saldo_cuenta;
    }

    @Override
    public void run() {
        /*
         * Llamamos a el metodo sincronizado el cual va a restringir o no el paso
         */

        cuentaCorriente.anadirSaldo(saldo_cuenta);
    }
}
