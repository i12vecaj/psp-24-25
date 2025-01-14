import java.net.InetAddress;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //Creamos un Scanner para leer texto de la consola
        String entrada; //Declaramos una variable para almacenar la entrada del usuario

        while (true) {
            System.out.println("Indique la URL o direccion IP del sitio que desea ver la informacion: (Para salir introduzca 'localhost')");
            entrada = scanner.nextLine(); //Leemos la informacion que introduzca el usuario

            if (entrada.equalsIgnoreCase("localhost")){ //Si introducen localhost cerraremos el programa
                System.out.println("Programa finalizado...");
                break;
            }

            if (entrada.isEmpty())//Comprobamos si la entrada esta vacia
            {
                System.out.println("Por favor, indique alguna URL o direccion IP.\n");
                continue;
            }

            try
            {
                InetAddress direccion = InetAddress.getByName(entrada);//Creamos una variable direccion que almacena la informacion de entrada

                System.out.println("Informacion de la direccion:\n");
                System.out.println("Nombre del host:"+direccion.getHostName());//Aqui mostraremos el hostname asociado con la IP
                System.out.println("Direccion IP:"+direccion.getHostAddress());//Aqui devolvemos la IP en formato texto
                System.out.println("Nombre del dominio completo:"+direccion.getCanonicalHostName());//Obtemos el nombre canonico del host
                System.out.println("La direccion es:"+ direccion.toString()+"\n");//Mostramos el nombre del host y la IP juntos

            } catch (Exception e) {
                System.out.println("Error: No se pudo resolver la URL/IP introducida.");
            }
        }
        scanner.close();//Cerramos el scanner
    }
}