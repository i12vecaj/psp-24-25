import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9876);
            List<InetAddress> clientAddresses = new ArrayList<>();
            List<Integer> clientPorts = new ArrayList<>();
            Scanner sc = new Scanner(System.in);
            String mensaje;
            String respuesta;

            do {
                System.out.println("Escribe un mensaje para los Clientes:");
                mensaje = sc.nextLine();
                byte[] buffer = mensaje.getBytes();

                for (int i = 0; i < clientAddresses.size(); i++) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, clientAddresses.get(i), clientPorts.get(i));
                    socket.send(packet);
                }

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);
                respuesta = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Respuesta del Cliente: " + respuesta);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                if (!clientAddresses.contains(clientAddress) || !clientPorts.contains(clientPort)) {
                    clientAddresses.add(clientAddress);
                    clientPorts.add(clientPort);
                }
            } while (!mensaje.equals("Adios") && !respuesta.equals("Adios"));

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}