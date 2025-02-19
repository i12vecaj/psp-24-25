import java.io.*;
import java.net.*;

public class UDPClient {
    private static final String SERVER_IP = "localhost"; //dirección IP del servidor
    private static final int SERVER_PORT = 9876;         //duerto del servidor
    private static final int TIMEOUT = 5000;            //tiempo de espera en milisegundos

    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            clientSocket.setSoTimeout(TIMEOUT); //establecer tiempo de espera para receive()

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            while (true) {
                System.out.print("Introduce un texto (o * para salir): ");
                String message = userInput.readLine();

                //verificar si el usuario quiere salir
                if ("*".equals(message)) {
                    System.out.println("Cliente cerrado.");
                    break;
                }

                //convertir el mensaje a bytes y enviarlo al servidor
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                //recibir la respuesta del servidor
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    clientSocket.receive(receivePacket);
                    String modifiedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Respuesta del servidor: " + modifiedMessage);
                } catch (SocketTimeoutException e) {
                    System.out.println("El paquete se ha perdido o el servidor no responde.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
