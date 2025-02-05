package ClienteServidorUDP;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class clienteUDP {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String fraseMinuscula;

        int puerto = 3500;
        InetAddress local = InetAddress.getLocalHost();
        while (true){

            byte[] mensaje = new byte[1024];

            System.out.println("Envie un mensaje al servidor: ");
            fraseMinuscula = scanner.nextLine();

            mensaje = fraseMinuscula.getBytes();

            DatagramPacket enviarFrase = new DatagramPacket(mensaje,mensaje.length,local,puerto);

            DatagramSocket socket = new DatagramSocket(16000);

            socket.send(enviarFrase);

            socket.close();
        }

    }
}
