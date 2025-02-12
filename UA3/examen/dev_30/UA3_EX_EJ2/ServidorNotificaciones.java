package UA3_EX_EJ2;

import java.io.*;
import java.net.*;

public class ServidorNotificaciones {

    //Considero que el mejor protocolo par usar en estos casos es el protocolo UDP, ya que lo que queremos es rapidez sin importar la perdida de datos
    // ya que se trata de enviar mensajes
    // a todos los clientes que esten esperando un mensaje del servidor, y no hay necesidad de que los clientes envien mensajes al servidor como confirmación
    // como pasaria en TCP.
    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        MulticastSocket ms = new MulticastSocket();

        int Puerto = 10008;
        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        String cadenaEnvio="";

        while(true) {
            System.out.print("Enviar Aviso a todos los clientes: ");
            cadenaEnvio = in.readLine();
            DatagramPacket paquete = new DatagramPacket(cadenaEnvio.getBytes(), cadenaEnvio.length(), grupo, Puerto);
            ms.send (paquete);
        }
    }
}
