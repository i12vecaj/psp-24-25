package tarea2_javiergarcia;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tarea2_JavierGarcia {

    
    public static void main(String[] args) {

		Scanner entrada=new Scanner(System.in);
		
		//Se crean las variables y se inicializan
                String caracteres="";
                String guardar="";
                
                //Se le pide al usuario que introduzca los caracteres
                System.out.println("Introduce la cantidad de caracteres que desee");
                System.out.println("Para acabar introduzca *");
                    
               //creamos un while para que introduzca los caracteres que desee hasta que introduzca "*" 
                while(!caracteres.equals("*")){
                    //guardamos los caracteres en otra veriable
                    guardar+=caracteres;
                    caracteres=entrada.nextLine();
                }
                
                //Se muestran la lista de caracteres
                System.out.println("Elementos guardados: "+guardar);
                
	}
        
        
    }
    

