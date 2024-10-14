package ua1tarea2;

import java.util.Scanner;

public class Hilo implements Runnable{

    @Override // utilizo el override puesto que nuestra clase extiende de Thread
    public void run(){ // Llamo al constructor del hilo
        String cadena;
        String cadenaFinal = "";
        // Defino el scanner para poder leer
        Scanner scan = new Scanner(System.in);
        // Recorro el bucle hasta que el usuario escriba *
        do {
            System.out.print("Digame una cadena de carácteres: ");
            cadena = scan.nextLine();
            if(!cadena.equals("*")){
                cadenaFinal += " " + cadena;
            }
        } while (
                !cadena.equals("*")
        );
        // Cierro el scanner e imprimo la cadena final
        scan.close();
        System.out.println(cadenaFinal);
    }
}
