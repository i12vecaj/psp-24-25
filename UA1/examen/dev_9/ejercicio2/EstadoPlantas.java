package org.example;

public class EstadoPlantas implements Runnable{
    public int contador;
    public String nombre;
    public long startTime;

    public EstadoPlantas (String nombre) {
        contador = 0;
        this.nombre = nombre;
        this.startTime = System.currentTimeMillis();
    }


    @Override
    public void run() {
        while(contador < 10) {

            double estado = Math.random() * 100;
            int numero = (int) (Math.random() * 3000) + 1;

            long tiempo = (System.currentTimeMillis() -startTime);

            System.out.println("El estado de la planta en el sensor " +
                    nombre +" es de "  + estado + " en " + tiempo + " milisegundos");

            //simulamos que la lectura tarda un tiempo aleatorio en realizarse en milisegundos
            try {

                Thread.sleep(numero);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contador++;

        }
    }
}
