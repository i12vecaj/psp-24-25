import java.net.*;

public class UDPServer {
    private static final int PORT = 9876; //puerto en el que escucha el servidor

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP iniciado en el puerto " + PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Mensaje recibido: " + message);

                //verificar si el cliente quiere cerrar la conexión
                if ("*".equals(message)) {
                    System.out.println("Cliente ha solicitado cerrar la conexión.");
                    break;
                }

                //convertir el mensaje a mayúsculas
                String modifiedMessage = message.toUpperCase();

                //enviar la respuesta al cliente
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                byte[] sendData = modifiedMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
