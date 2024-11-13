package com.tarea_2;

public class Main {
    public static void main(String[] args) {

        CuentaCorriente cuentaCorriente = new CuentaCorriente(40000);

        Thread gastador1 = new Thread(new Gastador(cuentaCorriente));
        Thread gastador2 = new Thread(new Gastador(cuentaCorriente));

        gastador1.start();
        gastador2.start();
    }
}
