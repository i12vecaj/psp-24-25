package org.example;

import java.util.ArrayList;
import java.util.Random;
/*
* EXAMEN: 11/12/2024
* Autor: Juan Deogracias Moya
* */

/**
 * @brief
 *
 *                     Esta clase se encarga de instanciar las clases pertinentes para que funcione el
 *                     programa
 *
 * @class              Class Name: Main
 */
public class ua2ex2 {
    public static void main(String[] args) {
        //ArrayList<Character> buffer =new ArrayList<>();
        //buffer.add(generarCaracterAleatorio());
        //System.out.println(buffer.get(0));
        System.out.println("\t------------EMPEZEMOS------------");
        Buffer almacenamiento = new Buffer(10);
        String[] nombres = {"Juan","Manolo","Enrique","Manuel","Jose David","Fernando",
                "Alejandro","Eduardo","Manuel Torres","Jose David Vela"};

        ArrayList<Productor> productores = new ArrayList<Productor>();
        ArrayList<Consumidor> consumidores = new ArrayList<Consumidor>();


        for(int i=0; i<20;i++) {
            productores.add(new Productor(almacenamiento, nombres[generarNumeroAleatorio()]));
            productores.get(i).start();
        }

        for(int i=0; i<10;i++) {
            consumidores.add(new Consumidor(almacenamiento, nombres[generarNumeroAleatorio()]));
            consumidores.get(i).start();
        }

        try {
            //Esperamos un tiempo para poder introducir el total de caracteres
            Thread.sleep(30000);
            System.out.println("***CARACTERES FINALES***");
            for(Character a : almacenamiento.buffer) {
                System.out.println("\t" + a);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //char caracter = (char)(Math.random()*26+'A');
        //System.out.println("caracter = " + caracter);

        //Buffer buffer = new Buffer(10); //Capacidad de 10 predefinida



    }

    public static int generarNumeroAleatorio() {
        Random random = new Random();
        return random.nextInt(9);
    }

    //Devolvemos un caracter aleatorio
    public static char generarCaracterAleatorio() {
        return (char)(Math.random()*26+'A');
    }
    /**
     * @brief
     *
     *                     Clase compartida por los hilos para poder utilizar los recursos de la misma
     *
     * @class              Class Name: Buffer
     */

    public static class Buffer {
        public ArrayList<Character> buffer =new ArrayList<>();
        public int indiceEscritura;
        public int indiceLectura;
        public int capacidad;
        public boolean disponibilidad = true;
        public Random random = new Random();

        public Buffer(int capacidad) {
            llenarArray();
            this.indiceEscritura = 0;
            this.indiceLectura = 0;
            this.capacidad = capacidad;
        }

        public void llenarArray () {
            for(int i=0;i<10;i++) {
                buffer.add(generarCaracterAleatorio());
            }
        }

        //En este metodo vamos a hacer que el productor producca
        public synchronized void consumir() throws InterruptedException {
            while(!disponibilidad) {
                System.out.println("El consumidor " + Thread.currentThread().getName() +
                        " esperando a que haya disponibilidad para poder consumir");
                wait();
            }
            System.out.println("\n\t\tCONSUMIDOR\n" +"\t***"+Thread.currentThread().getName() + " EMPEZANDO***");

            int indice_a_consumir = generarIndiceAleatorio();
            System.out.println("El consumidor "+ Thread.currentThread().getName() +" esta onsumiendo...");
            if(buffer.size() < indice_a_consumir) {
                System.out.println("El indice a consumir se sale de los rangos");
                return;
            }
            System.out.println("Eliminando el caracter de la posicion "+ indice_a_consumir +
                    " con el valor de " + buffer.get(indice_a_consumir));
            //Procedemos a eliminar el caracter
            buffer.remove(indice_a_consumir);
            System.out.println("\tCaracter eliminado\n" +"\t***"+Thread.currentThread().getName() + " TERMINADO***\n");

            if(buffer.isEmpty()) {
                disponibilidad = false;
            }
            notifyAll();
        }

        public int generarIndiceAleatorio() {
            if(!buffer.isEmpty()) {
                int tamano = buffer.size();
                return random.nextInt(tamano);
            }else {
                buffer.add(generarCaracterAleatorio());
                return 0;
            }

        }
        public synchronized void producir(char caracter_aleatorio) throws InterruptedException {

            Thread.sleep(1000);

            System.out.println("\n\t\tPRODUCTOR\n" +"\t***"+Thread.currentThread().getName() + " EMPEZANDO***");

            System.out.println("El productor " + Thread.currentThread().getName()
                    + " esta generando un caracter...");
            Thread.sleep(tiempoEsperaAleatorioProducir());

            //Añadimos el caracter aleatorio
            buffer.add(caracter_aleatorio);
            System.out.println("Caracter añadido: " + caracter_aleatorio);
            disponibilidad = true;
            System.out.println("\tCARACTER AÑADIDO\n" +"\t***"+Thread.currentThread().getName() + " TERMINADO***\n");
            notifyAll();
        }

    }

    public static int tiempoEsperaAleatorioProducir() {
        Random random = new Random();
        return 200 + random.nextInt(301);
    }
    public static int tiempoEsperaAleatorioConsumir() {
        Random random = new Random();
        return 300 + random.nextInt(401);
    }
    /**
     * @brief
     *
     *                     Clase que se encarga de producir un recurso en este caso un caracter aleatorio
     *                     de la clase Buffer concretamente de el tipo buffer el array de char
     *
     * @class              Class Name: Productor
     */
    public static class Productor extends Thread{
        public Buffer buffer;
        public String nombre;

        public Productor(Buffer buffer, String nombre) {
            this.buffer = buffer;
            setName(nombre);
            this.nombre = nombre;
        }
        public void run() {
            try {
                buffer.producir(generarCaracterAleatorio());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * @brief
     *
     *                     Clase encargada de consumir un recurso que en este caso es
     *                     el arraylist buffer de la clase Buffer
     *
     * @class              Class Name: Consumidor
     */
    public static class Consumidor extends Thread{
        public Buffer buffer;
        public String nombre;

        public Consumidor(Buffer buffer, String nombre) {
            this.buffer = buffer;
            setName(nombre);
            this.nombre = nombre;
        }

        public void run() {
            try {
                buffer.consumir();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
