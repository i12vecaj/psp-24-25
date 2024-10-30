package org.example;

public class Humedad implements Runnable{

    public double cantidad_humedad;
    public int contador;
    public String nombre;
    public long startTime;

    public Humedad(String nombre) {
        this.contador = 0;
        this.cantidad_humedad= 0.0f;
        this.nombre = nombre;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while(contador < 10) {

                    double humedad = Math.random() * 100;

                    int numero = (int) (Math.random() * 3000) + 1;

                    long tiempo = (System.currentTimeMillis() -startTime);

                    System.out.println("La humedad en el sensor " + nombre +" es de " + humedad +
                            " % en el tiempo de " + tiempo);

            //simulamos que la lectura tarda un tiempo aleatorio en realizarse en milisegundos
                    try {

                        Thread.sleep((long) numero);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    contador++;

        }





        }
}
