package org.example;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        /*
        * Creamos la instancia de la clase hilo y creamos una instancia de la clase thread que contiene la clase hilo
        * que va a ser ejecutada y recibirá como argumento args que será lo que recibe de la terminal a la hora de crear
        * el programa.
        * */

        Hilo hilo = new Hilo(args);

        Thread proceso = new Thread(hilo);

        proceso.start();

        /*
        * Hacemos un join para poder escribir texto después de que finalize la ejecución de el hilo
        * */
        try{
            proceso.join();

        }catch (Exception e) {
            e.printStackTrace();
        }


        /*
        * Comprobamos el exitCode de la clase hilo y el funcion de lo que haga hacemos una cosa u otraº
        * */
        switch (hilo.getExitCode()) {
            case 0:
                System.out.println("Has introducido un resultado que no teniamos contemplado");
                System.exit(hilo.exitCode);

            case 1:
                System.out.println("No has introducido nada. Vuelve a intentarlo");
                System.exit(hilo.exitCode);
            case 2:
                System.out.println("Has introducido una cadena de texto");
                System.exit(hilo.exitCode);

            case 3:
                System.out.println("El numero es negativo");
                System.exit(hilo.exitCode);
            default:
                System.out.println("No entiendo lo que has introducido. Vuelve a intentarlo");
                System.exit(hilo.exitCode);
        }

        System.out.println("Ha finalizado el programa");

    }
}