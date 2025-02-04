import java.io.IOException;
import java.net.*;

// SERVIDOR UDP

public class ua3tarea4fr1 {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(9876); // El servidor esta en el 9876
            System.out.println("servidor en ejecucion esperando al mensaje...");

            while (true) {
                byte[] recibirDatos = new byte[1024];
                DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
                serverSocket.receive(recibirPaquete); // aqui recibo el mensaje (cliente)

                String mensaje = new String(recibirPaquete.getData(), 0, recibirPaquete.getLength());
                System.out.println("el mensaje ha sido recibido: " + mensaje);


                if (mensaje.equals("*")) {
                    System.out.println("ASTA PRONTO, * RECIBIDO : Saliendo... :)");
                    break;
                }


                String mensajeMayusculas = mensaje.toUpperCase();
                byte[] enviarDatos = mensajeMayusculas.getBytes();


                InetAddress IPCliente = recibirPaquete.getAddress();
                int puertoCliente = recibirPaquete.getPort();


                DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, IPCliente, puertoCliente);
                serverSocket.send(enviarPaquete);
            }
        } catch (IOException e) {
            System.err.println("Error de IO en el servidor: " + e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}


