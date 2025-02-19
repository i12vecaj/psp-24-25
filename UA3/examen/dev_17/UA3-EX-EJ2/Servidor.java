//Revisado por JD: 19/02/25

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/*En este ejercicio he decidido usar el modelo UDP con multicast, para poder mandar informacion a distintos clientes
* ya que solo deberian estar en el mismo grupo y asi no habria problema al momento de conctarse, he usado el modelo UDP
* por que al ser mensajes cortos como notificaciones no habra problema en la perdida de informacion y tambien por la
* velocidad de envio.*/

public class Servidor {
    public static void main(String[] args) throws IOException {
        // FLUJO PARA ENTRADA ESTANDAR
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));

        //Se crea el socket multicast.
        MulticastSocket servidorMultiCast = new MulticastSocket();

        int Puerto = 12345;//Puerto multicast
        InetAddress grupo = InetAddress.getByName("225.0.0.1");//Grupo

        String notificacion = "";

        while(!notificacion.trim().equals("*")) {
            System.out.print("Notificacion a enviar: ");
            notificacion = lector.readLine();
            // ENVIANDO AL GRUPO
            DatagramPacket paquete = new DatagramPacket
                    (notificacion.getBytes(), notificacion.length(), grupo, Puerto);
            servidorMultiCast.send (paquete);
        }
        servidorMultiCast.close ();//cierro socket
        System.out.println ("Servidor cerrado...");
    }
}
