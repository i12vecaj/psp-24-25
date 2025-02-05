package ClienteServidorUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;


public class servidorUDP {
    public static void main(String[] args) throws IOException {
        String mensajeMayusculas = "";
        DatagramSocket socket = new DatagramSocket(3500);

        do{
            byte[] buferRecibir = new byte[12];

            try {

                DatagramPacket recibir = new DatagramPacket(buferRecibir,buferRecibir.length);
                System.out.println("Esperando a un datagrama....");
                socket.receive(recibir);

                String  paquete = new String(recibir.getData(),0, recibir.getLength());
                mensajeMayusculas = paquete.toUpperCase();

                System.out.println(mensajeMayusculas);
                
            }catch (IOException e){
                System.out.println("Se han perdido Datos. "+e);
            }
        }while (!mensajeMayusculas.equals("*"));

    }
}
