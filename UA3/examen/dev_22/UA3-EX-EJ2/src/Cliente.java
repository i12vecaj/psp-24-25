import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("localhost");
            Scanner sc = new Scanner(System.in);
            String mensaje;
            String respuesta;

            String registro = "Cliente conectado";
            byte[] buffer = registro.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 9876);
            socket.send(packet);

            do {
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                mensaje = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Mensaje del SERVIDOR: " + mensaje);

                InetAddress serverAddress = receivePacket.getAddress();
                int serverPort = receivePacket.getPort();
                System.out.println("Escribe un mensaje para el SERVIDOR:");
                respuesta = sc.nextLine();
                buffer = respuesta.getBytes();
                packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
                socket.send(packet);
            } while (!mensaje.equals("Adios") && !respuesta.equals("Adios"));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}