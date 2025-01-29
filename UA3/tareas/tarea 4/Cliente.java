import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try (DatagramSocket clienteSocket = new DatagramSocket();
             Scanner sc = new Scanner(System.in)) {

            clienteSocket.setSoTimeout(5000);
            InetAddress servidorIP = InetAddress.getLocalHost();
            int puerto = 6000;

            while(true){
                System.out.print("Introduce una frase (* para salir): ");
                String mensaje = sc.nextLine();

                byte[] enviar = mensaje.getBytes();
                DatagramPacket paqueteEnviado = new DatagramPacket(enviar, enviar.length, servidorIP, puerto);
                clienteSocket.send(paqueteEnviado);

                if (mensaje.equals("*")) {
                    System.out.println("Cerrando cliente...");
                    break;
                }

                byte[] recibido = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(recibido, recibido.length);

                try {
                    clienteSocket.receive(paqueteRecibido);
                    String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. Puede que el paquete se haya perdido.");
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
