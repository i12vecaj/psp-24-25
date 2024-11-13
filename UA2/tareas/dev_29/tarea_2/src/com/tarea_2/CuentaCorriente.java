package com.tarea_2;

public class CuentaCorriente {
    private float saldo;

    public CuentaCorriente(float saldo) {
        this.saldo = saldo;
    }

    public float consultarSaldo() {
        try{
            Thread.sleep((long) Math.random());
        }catch (InterruptedException e){}
        return this.saldo;
    }

    public void actualizarSaldo(float saldo) {
        float oldSaldo = this.saldo;
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){}

        this.saldo += saldo;
    }

    public void notificarSaldo(float oldSaldo, String usuario) {
        System.out.println("Operacion realizada por: " + usuario + "\tSu saldo ha pasado de " + oldSaldo + " a " + this.saldo);
    }
}
