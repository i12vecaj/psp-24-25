import java.io.*;
import java.net.*;
import java.util.Scanner;

public class tarea4cliente {

    public static void main(String[] args) throws IOException {

        Scanner escanner = new Scanner(System.in);

        Socket cliente = new Socket("localhost", 2000);
        String enviarmensaje = "";
        String recibidomensaje = "";

        String letra = "";

        System.out.println("Introduce el texto:  ");

        boolean fin = false;

        while (!letra.equals("*")) {
            letra = escanner.next();
            if (!letra.equals("*")) {
                recibidomensaje += letra;
            }
        }

        PrintWriter fuera = new PrintWriter(cliente.getOutputStream(), true);
        fuera.println(recibidomensaje);

        System.out.println("Envio realizado");

        BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        String mensaje = in.readLine();

        System.out.println("Mensaje del servidor:" + mensaje);

        cliente.close();
    }

}
