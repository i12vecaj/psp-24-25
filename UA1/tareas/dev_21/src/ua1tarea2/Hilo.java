package ua1tarea2;

import java.util.Scanner;
public class Hilo extends Thread{
    @Override
    public void run(){
        Scanner scanner =new Scanner(System.in);
        String carater="";
        do{
            System.out.printf("Introduce un caracter(* para detener el programa): ");
            carater = scanner.nextLine();
        }while(!(carater.equals("*")));
    }
}