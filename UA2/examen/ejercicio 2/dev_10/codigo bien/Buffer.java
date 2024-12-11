package org.example;

import java.util.Random;

class Buffer {
    private char[] buffer;
    private int capacidad;
    private int indiceEscritura;
    private int indiceLectura;
    private int contador;

    public Buffer(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new char[capacidad];
        this.indiceEscritura = 0;
        this.indiceLectura = 0;
        this.contador = 0;
    }

    /**
     *@brief              Metodo producir un caracter para el buffer
     *
     *                    El metodo produce un caracter para buffer.
     *
     *@param              char  los parametros de entrada son el caracter a producir.
     *
     *@return             no returna nada
     *@retval             Los valores de retorno son:
     */

    public synchronized void producir(char caracter) throws InterruptedException {
        while (contador == capacidad) {
            wait();
        }
        buffer[indiceEscritura] = caracter;
        indiceEscritura = (indiceEscritura + 1) % capacidad;
        contador++;
        System.out.println("Producido: " + caracter + ". Buffer: " + String.valueOf(buffer));
        notifyAll();
    }

    /**
     *@brief              Metodo consumir un caracter del buffer
     *
     *                    El metodo consume un caracter del buffer y lo retorna.
     *
     *@param              void            No hay parametros de entrada.
     *
     *@return             Return caracter            Caracter consumido del buffer.
     *@retval             Los valores de retorno son: Caracter consumido del buffer.(no se que mas poner la vrdad :))
     */
    public synchronized char consumir() throws InterruptedException {
        while (contador == 0) {
            wait();
        }
        char caracter = buffer[indiceLectura];
        indiceLectura = (indiceLectura + 1) % capacidad;
        contador--;
        System.out.println("Consumido: " + caracter + ". Buffer: " + String.valueOf(buffer));
        notifyAll();
        return caracter;
    }
}
