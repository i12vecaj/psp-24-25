import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ChatServer {
    private static final int Puerto = 10100;
    private static ArrayList<InetAddress> clientes = new ArrayList<>();
    private static ArrayList<Integer> PuertoCliente = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(Puerto);
        System.out.println("Servidor esperando mensajes...");

        while (true) {
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String mensaje = new String(packet.getData(), 0, packet.getLength());
            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();

            // Guardar dirección y puerto del cliente si no está ya en la lista
            if (!clientes.contains(clientAddress)) {
                clientes.add(clientAddress);
                PuertoCliente.add(clientPort);
            }
            
            System.out.println("Mensaje recibido de " + clientAddress + ", Puerto:" + clientPort + " - " + mensaje);
            enviarAMultiplesClientes(socket, mensaje, clientAddress, clientPort);
        }
    }
        //Recivir varios clientes al mismo tiempo
    private static void enviarAMultiplesClientes(DatagramSocket socket, String mensaje, InetAddress senderAddress, int senderPort) throws IOException {
        for (int i = 0; i < clientes.size(); i++) {
            if (!(clientes.get(i).equals(senderAddress) && PuertoCliente.get(i) == senderPort)) {
                byte[] response = mensaje.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(response, response.length, clientes.get(i), PuertoCliente.get(i));
                socket.send(responsePacket);
            }
        }
    }
}