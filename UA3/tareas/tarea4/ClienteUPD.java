import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class ClienteUDP {

    public static void main(String[] args) {
        final String SERVIDOR_IP = "localhost";
        final int PUERTO_SERVIDOR = 6789;
        final int TIMEOUT = 5000; // 5 segundos

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT);

            InetAddress direccionServidor = InetAddress.getByName(SERVIDOR_IP);
            BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Introduce un mensaje (o * para salir): ");
                String mensaje = entradaUsuario.readLine();

                byte[] bufferEnvio = mensaje.getBytes();
                DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, PUERTO_SERVIDOR);
                socket.send(paqueteEnvio);

                if (mensaje.equals("*")) {
                    System.out.println("Cliente finalizado.");
                    break;
                }

                byte[] bufferRecepcion = new byte[1024];
                DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

                try {
                    socket.receive(paqueteRecepcion);
                    String mensajeRecibido = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                    System.out.println("Mensaje recibido del servidor: " + mensajeRecibido);
                } catch (SocketTimeoutException e) {
                    System.out.println("El paquete se ha perdido o el servidor no responde.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
