package com.tarea_2;

public class Gastador implements Runnable {

    CuentaCorriente cuentaCorriente;

    public Gastador(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    @Override
    public void run() {
        synchronized (cuentaCorriente){
            float oldSaldo = cuentaCorriente.consultarSaldo();
            cuentaCorriente.actualizarSaldo(-100);
            cuentaCorriente.notificarSaldo(oldSaldo, Thread.currentThread().getName());
        }
    }
}
