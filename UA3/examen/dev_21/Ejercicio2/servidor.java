import java.io.*;
import java.net.*;

public class servidor {
    public static void main(String args[]) throws Exception {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

        MulticastSocket ms = new MulticastSocket();

        int Puerto = 5000;
        InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo

        String cadena="";

        while(!cadena.trim().equals("*")) {
            System.out.print("Datos a enviar al Equipo: ");
            cadena = entrada.readLine();


            DatagramPacket paquete = new DatagramPacket
                    (cadena.getBytes(), cadena.length(), grupo, Puerto);
            ms.send (paquete);
        }
        ms.close ();
        System.out.println ("Socket cerrado...");
    }
}