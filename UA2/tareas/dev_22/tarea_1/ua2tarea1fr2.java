package ua2.tareas.dev_22.tarea_1;

import java.util.Random;

public class ua2tarea1fr2 extends Thread {

    private String nombre;

    public ua2tarea1fr2(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random numAleatorio = new Random();
        int duracion = 5000 + numAleatorio.nextInt(8000); //Generar numeros aleatorios

        System.out.println("" + nombre + "esta comenzando. Tiene una duracion de: " + (duracion/1000) + " segundos.");

        try {
            System.out.println("" + Thread.currentThread().getName());
            Thread.sleep(duracion);

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("La atraccion " + nombre + "ha terminado");
    }



}
