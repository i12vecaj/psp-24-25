package org.example;

public class Humedad implements Runnable{

    public double cantidad_humedad;
    public int contador;
    public String nombre;
    public long startTime;
    public int estado;

    public Humedad(String nombre) {
        this.contador = 0;
        this.cantidad_humedad= 0.0f;
        this.nombre = nombre;
        this.startTime = System.currentTimeMillis();
        estado = 0;
    }

    @Override
    public void run() {
        while(contador < 10) {

                    double humedad = Math.random() * 100;

                    int numero = (int) (Math.random() * 3000) + 1;

                    long tiempo = (System.currentTimeMillis() -startTime);

                    String humedad_redondeada = String.format("%.2f",humedad);

                    System.out.println("La humedad en el sensor " + nombre +" es de " + humedad_redondeada +
                            " % en el tiempo de " + tiempo + " milisegundos");

                    this.estado = probarTest();

            //simulamos que la lectura tarda un tiempo aleatorio en realizarse en milisegundos
                    try {

                        Thread.sleep((long) numero);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    contador++;

        }





        }
    public int probarTest() {

        this.estado = 1;

        return 1;
    }
}
