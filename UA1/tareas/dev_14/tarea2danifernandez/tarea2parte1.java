


import java.io.IOException;
import java.util.Scanner;

public class tarea2parte1 {
     public static void main(String[] args) throws IOException{
                Scanner scan = new Scanner(System.in);
              // Declaramos una variable cantidad, que será el rango en el bucle for
                int cantidad;
                cantidad = 1;
                       // Declaramos un vector grande para poder trabajar un gran numero de veces
                String cadena[] = new String[100];
                         // Trabajaremos con un bucle for que lo hará hasta la cantidad indicada dentro de nuestro código, lo cual mostraremos a continuacion
                for(int i=0;i<cantidad;i++){
                    System.out.println( "Escriba una cadena: ");
                    cadena[i]=scan.nextLine();
                   // Si la cadena no contiene un asterisco, la cantidad se incrementará para poder seguir introduciendo datos
                    if (!cadena[i].contains("*"))
                    {
                        cantidad++;
                    }
                   // Si la cadena contiene un asterisco , la cantidad se quedará tal y como estaba antes de introducir el asterisco
                    if(cadena[i].contains("*")){
                        i=cantidad;
                    }
            }
                        System.out.println("");
                        System.out.println("Resultado: ");
                        // Usamos un bucle for para mostrar los datos
                for(int j=0;j<cantidad;j++){

                    // Si la cadena no contiene un asterisco, mostrará todos aquellos valores que no lo contengan
                     if (!cadena[j].contains("*") )
                     {
                         System.out.println(cadena[j]);
                     }
                 }
            }
}








    
