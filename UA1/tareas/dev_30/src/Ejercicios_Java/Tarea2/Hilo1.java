package Ejercicios_Java.Tarea2;

import java.util.Scanner;

public class Hilo1 extends Thread {

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String caracter;

        boolean verdad;
        do {
            System.out.println("Ahora se le va a pedir un valor hasta que introduzca el valor * ");
            caracter = scanner.nextLine();
            if (caracter.equalsIgnoreCase("*")) {
                verdad = false;
                System.out.println("Has introducido el valor * ¡ADIOS!");
            } else {
                System.out.println("Has introducido: " + caracter);
                verdad = true;
            }
        }while (verdad == true);
    }
}
