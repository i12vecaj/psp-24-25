package org.example;

import java.util.Scanner;

public class hilo_2 extends Thread{

    /*
     * HILO INTRODUCCION QUE VA A DEVOLVER INSTRUCCIONES
     * */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Te dejo aqui dos opciones: \n\t1.Ejecutar el segundo hilo\n\t" +
                "2.Leer manual de instrucciones");

        int opciones = scanner.nextInt();

        switch (opciones) {
            case 1:
                System.out.println("Perfecto ejecutemos el siguiente hilo");
                break;
            case 2:
                System.out.println("Si pones * te saldrás");
                break;
            default:
                System.out.println("Opción incorrecta. Seleccione un número entre 1 y 2.");
        }
    }
}
