package org.example;

public class Temperatura implements Runnable{

    public float temperature;
    public String nombre_sensor;
    public int contador;
    public long startTime;
    public int estado;

    public Temperatura(String nombre_sensor) {
        this.temperature = temperature;
        this.contador = 0;
        this.nombre_sensor = nombre_sensor;
        this.startTime = System.currentTimeMillis();
        estado = 0;
    }


    @Override
    public void run() {
        while(contador < 10) {

            double humedad = Math.random() * 100;

            //creamos el numero aleatorio y el tiempo en el que se ha creado
            int numero = (int) (Math.random() * 3000) + 1;
            long tiempo = (System.currentTimeMillis() -startTime);

            System.out.println("La temperatura en el sensor " + nombre_sensor + " es de " + humedad + " grados en el tiempo de " +
                    " en el tiempo de " + tiempo);

            //simulamos que la lectura tarda un tiempo aleatorio en realizarse en milisegundos
            try {

                Thread.sleep(numero);
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
