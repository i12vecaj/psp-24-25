import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class servidorUDP {
    public static void main(String[] argv) throws Exception {
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            System.out.println("Servidor esperando mensajes...");

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String( receivePacket.getData());
            System.out.println("Mensaje recibido: " + sentence);

            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
