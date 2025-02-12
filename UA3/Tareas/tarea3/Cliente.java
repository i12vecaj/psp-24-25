import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPCliente {
    public static void main(String[] args) {
        final String SERVIDOR_IP = "127.0.0.1";
        final int PUERTO_SERVIDOR = 6000;
        final int PUERTO_CLIENTE = 7000;
        byte[] buffer = new byte[1024];

        try {

            DatagramSocket socket = new DatagramSocket(PUERTO_CLIENTE);
            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR_IP);


            Scanner scanner = new Scanner(System.in);
            System.out.print("Escribe un mensaje: ");
            String mensaje = scanner.nextLine();


            byte[] mensajeEnviado = mensaje.getBytes();
            DatagramPacket paqueteEnviado = new DatagramPacket(mensajeEnviado, mensajeEnviado.length, direccionServidor, PUERTO_SERVIDOR);
            socket.send(paqueteEnviado);


            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);


            String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Respuesta del servidor: " + respuesta);


            socket.close();
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
