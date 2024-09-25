import java.net.*;
import java.util.Scanner;
/*Crea un programa en Java que admita desde la 
línea de comandos una URL y visualice información sobre esta. Modifica el programa para que admita continuamente 
nuevas IP o URL y muestre la información hasta que el usuario inserte "localhost".*/
public class Tarea1 {

    public static void main(String[] args) {
        InetAddress dir = null;
        
        Scanner scan = new Scanner(System.in);

        System.out.println("========================================================");
        System.out.println("SALIDA PARA LOCALHOST: ");
        try {
            //LOCALHOST
            dir = InetAddress.getByName("localhost");
            pruebaMetodos(dir);//

            //URL	www.google.es
           
 String direccion = "";
            do {
               
                System.out.println("Introduce la URL");
                System.out.println("Salir, localhost ");
                direccion = scan.next();
                dir = InetAddress.getByName(direccion);
                
                
                try {
	 	
	    System.out.println("========================================================");
		System.out.println("SALIDA PARA UNA URL:");
		dir = InetAddress.getByName(direccion);
		pruebaMetodos(dir);

	     // Array de tipo InetAddress con todas las direcciones IP
	     //asignadas a google.es
	     System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
	     InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
	     for (int i = 0; i < direcciones.length; i++)
	          System.out.println("\t\t"+direcciones[i].toString());

		System.out.println("========================================================");

	  } catch (UnknownHostException e1) {e1.printStackTrace();}
 
 
 
            } while (direccion.equals("localhost")==false);
            
           
           

            // Array de tipo InetAddress con todas las direcciones IP
            //asignadas a google.es
            System.out.println("\tDIRECCIONES IP PARA: " + dir.getHostName());
            InetAddress[] direcciones = InetAddress.getAllByName(dir.getHostName());
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println("\t\t" + direcciones[i].toString());
            }

            System.out.println("========================================================");

        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }
    }// main

    private static void pruebaMetodos(InetAddress dir) {
        System.out.println("\tMetodo getByName():  " + dir);
        InetAddress dir2;
        try {
            dir2 = InetAddress.getLocalHost();
            System.out.println("\tMetodo getLocalHost(): " + dir2);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // USAMOS METODOS DE LA CLASE
        System.out.println("\tMetodo getHostName(): " + dir.getHostName());
        System.out.println("\tMetodo getHostAddress(): " + dir.getHostAddress());
        System.out.println("\tMetodo toString(): " + dir.toString());
        System.out.println("\tMetodo getCanonicalHostName(): " + dir.getCanonicalHostName());
    }//pruebaMetodos
}//fin
