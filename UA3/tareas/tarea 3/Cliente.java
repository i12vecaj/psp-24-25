import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        //Se crea un socket UDP para que el cliente pueda comunicarse.
        DatagramSocket clienteSocket = new DatagramSocket();

        //Se obtiene la dirección IP del servidor (localhost en este caso).
        InetAddress servidorIP = InetAddress.getLocalHost();
        int puerto = 6000; // Puerto del servidor.

        //Solicita al usuario que introduzca un mensaje para enviar al servidor.
        System.out.print("Introduce una frase: ");
        String introducir = sc.nextLine();

        //Convierte el mensaje introducido en un array de bytes para enviarlo.
        byte[] enviar = introducir.getBytes();

        //Se crea un paquete UDP para enviar el mensaje al servidor.
        DatagramPacket paqueteEnviado = new DatagramPacket(enviar, enviar.length, servidorIP, puerto);
        clienteSocket.send(paqueteEnviado); // Se envía el paquete al servidor.

        //Prepara un buffer para recibir la respuesta del servidor.
        byte[] recibido = new byte[1024];
        DatagramPacket paqueteRecibido = new DatagramPacket(recibido, recibido.length);

        //Se queda bloqueado esperando la respuesta del servidor.
        System.out.println("Esperando respuesta del servidor...");
        clienteSocket.receive(paqueteRecibido);

        //Convierte la respuesta recibida en un String.
        String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
        System.out.println("Respuesta del servidor: " + respuesta);

        //Cierra el socket después de completar la comunicación.
        clienteSocket.close();
    }
}
