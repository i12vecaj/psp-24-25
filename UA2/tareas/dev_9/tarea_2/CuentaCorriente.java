package org.example;

import java.util.Random;

/*
* Clase que he querido que sea compartida por todos los compradores(Hilos)
* */
public class CuentaCorriente {
    /*
    * Saldo de la cuenta
    * */
    public float saldo_cuenta;
    /*
    * Creamos una instancia de la clase Random la cual va a generar un numero aleatorio
    * y va a ser usado en la funcion generarNumeroAleatorio
    * */
    public Random random = new Random();

    /*
    * Constructor que inicializa saldo_cuenta a 0.0f es decir a cero
    * */
    public CuentaCorriente() {
        this.saldo_cuenta = 0.0f;
    }

    /*
    * Funcion que genera una espera aleatoria entre 250 y 2000 milisegundos
    * */
    private void esperaAleatoria() {
        int tiempoEspera = 250 + random.nextInt(1751);
        try {
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    * Métodos getter y setter definidos por la actividad
    * */
    public float getSaldo_cuenta() {
        esperaAleatoria();
        return saldo_cuenta;
    }

    public void setSaldo_cuenta(float saldo_cuenta) {
        esperaAleatoria();
        this.saldo_cuenta = saldo_cuenta;
    }

    /*
     * Sincronizamos el metodo anadirSaldo de modo que solo pueda acceder un hilo
     * de este modo se controla las condiciones de carrera
     */
    public synchronized void anadirSaldo(float saldo) {
        esperaAleatoria();
        System.out.println("\n\t****\nEl comprador " + Thread.currentThread().getName() + " tiene una prioridad de " +
                            Thread.currentThread().getPriority());
        System.out.printf("Antes de añadir: %.2f\n", getSaldo_cuenta());
        saldo_cuenta += saldo;
        System.out.printf("Después de añadir: %.2f\n", getSaldo_cuenta());
        System.out.printf("Ingreso por: %s\n", Thread.currentThread().getName());
        getContent();
        notify();
    }

    public boolean getContent () {
        if(saldo_cuenta == 1000) {
            return true;
        }
        return false;
    }

}
