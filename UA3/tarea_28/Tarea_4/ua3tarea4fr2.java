import java.io.IOException;
import java.net.*;
import java.util.Scanner;

// CLIENTE UDP
public class ua3tarea4fr2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket clientSocket = null;

        try {
            // Aquie creo el socket
            clientSocket = new DatagramSocket();

            // obtengo la direccion ip y el puerto que sera el 9768
            InetAddress IPServidor = InetAddress.getLocalHost();
            int puerto = 9876;

            // esto es el tiempo de espera que sera 5 s
            clientSocket.setSoTimeout(5000);

            while (true) {

                System.out.print("Pon un mensaje y si quiere salir pon (*)");
                String mensaje = scanner.nextLine();

                // el mesaje lo convierto a bytes
                byte[] enviados = mensaje.getBytes();
                DatagramPacket envio = new DatagramPacket(enviados, enviados.length, IPServidor, puerto);

                clientSocket.send(envio);   // envio el paquete al servidor


                if (mensaje.equals("*")) {
                    System.out.println("finalizando cliente ASTA PRONTO...");
                    break;
                }

                try {
                    // recibir la respuesta del servidor
                    byte[] recibidos = new byte[1024];
                    DatagramPacket recibo = new DatagramPacket(recibidos, recibidos.length);
                    clientSocket.receive(recibo);

                    // aqui covierto los datos en texto
                    String respuesta = new String(recibo.getData(), 0, recibo.getLength());
                    System.out.println("Respuesta del servidor: " + respuesta);
                } catch (SocketTimeoutException e) {
                    System.out.println("El paquete se perdio al no haber respuesta del servidor");
                }
            }
        } catch (IOException e) {
            System.err.println("Error de IO: " + e.getMessage());
        } finally {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        }
    }
}

