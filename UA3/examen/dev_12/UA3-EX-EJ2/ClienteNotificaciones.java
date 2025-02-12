package examenua3.parte11;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteNotificaciones {
    private static final int PUERTO_SERVIDOR = 9000;
    private int puertoCliente;

    public ClienteNotificaciones(int puerto) {
        this.puertoCliente = puerto;
    }

    public void iniciar() {
        try (DatagramSocket socket = new DatagramSocket(puertoCliente)) {
            InetAddress servidorIP = InetAddress.getByName("localhost");

            // Registrar cliente en el servidor
            String mensajeRegistro = "REGISTRAR";
            byte[] bufferRegistro = mensajeRegistro.getBytes();
            DatagramPacket paqueteRegistro = new DatagramPacket(
                    bufferRegistro, bufferRegistro.length, servidorIP, PUERTO_SERVIDOR);
            socket.send(paqueteRegistro);

            System.out.println("Conectado al sistema de notificaciones. Esperando mensajes...");

            // Escuchar mensajes del servidor
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("\nNueva notificacion: " + mensaje);
            }
        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso: java ClienteNotificaciones [puerto]");
            return;
        }

        int puerto = Integer.parseInt(args[0]);

        ClienteNotificaciones cliente = new ClienteNotificaciones(puerto);
        cliente.iniciar();
    }
}
