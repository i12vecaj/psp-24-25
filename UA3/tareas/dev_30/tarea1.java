import java.net.*;
import java.util.Scanner;

public class tarea1 {
    URL url;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String direccion = "";


        try{

            do {

                System.out.println("Dime una direccion IP o una direccion web");
                direccion = scanner.nextLine();

                URL url = new URL(direccion);

                System.out.println("El protocolo que utiliza la pagina web es: "+url.getProtocol());
                System.out.println("El host que esta utilizando es: "+url.getHost());




            }while (!direccion.equals("localhost"));






        }catch (Exception e){
            if (direccion.equals("localhost"))

                System.out.println("Saliendo del programa....");
            else
                System.out.println("Ha ocurrido un error al mostrar la información");
        }





    }
}

