package org.example;

public class Aplicación_Principal {
    public static void main(String[] args) {
        try {
            Buffer buffer = new Buffer(10);
            Thread hiloProductor = new Thread(new Productor(buffer));
            Thread hiloConsumidor = new Thread(new Consumidor(buffer));

            hiloProductor.start();
            hiloConsumidor.start();

            Thread.sleep(10000);

            hiloProductor.interrupt();
            hiloConsumidor.interrupt();
            hiloProductor.join();
            hiloConsumidor.join();

        } catch (Exception error) {
            System.out.println("Error al ejecutar el programa: " + error)  ;
        }
    }
}


