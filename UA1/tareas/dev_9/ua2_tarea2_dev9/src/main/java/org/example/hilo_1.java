package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class hilo_1 extends Thread{

    public boolean condition = false;
    /*
     * Definimos una lista que recoga lo introducido por el usuario
     * */
    List<String> frases = new ArrayList<>();


    /*
     * HILO QUE EJECUTA LEE Y SE SALA SEGUN LO QUE INTRODUCE EL USUARIO
     * */
    public void run() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("Introduce un valor");

            String input = sc.nextLine();

            if (input.equals("*")) {
                condition = true;
                System.out.println("Ha sido un placer estar contigo");

                System.out.println("Te dejo por aqui el historial de tus respuestas :) {\n\t\t"
                        + String.join("\n\t\t", frases) +"\n\t}");
            } else {
                // Agregar la frase a la lista
                frases.add(input);
            }
        } while (!condition);
    }
}
