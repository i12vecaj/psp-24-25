import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

    private static List<ClienteHandler> clientes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int Puerto = 6000; // Puerto
        ServerSocket servidor = new ServerSocket(Puerto);
        System.out.println("Escuchando en " + servidor.getLocalPort());

        while (true) {
            Socket cliente = servidor.accept();
            ClienteHandler clienteHandler = new ClienteHandler(cliente);
            clientes.add(clienteHandler);
            new Thread(clienteHandler).start();
        }
    }

    public static void broadcastMessage(String mensaje, ClienteHandler sender) {
        for (ClienteHandler cliente : clientes) {
            if (cliente != sender) {
                cliente.enviarMensaje(mensaje);
            }
        }
    }

    static class ClienteHandler implements Runnable {
        private Socket socket;
        private DataInputStream entrada;
        private DataOutputStream salida;
        private String nombreUsuario;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
            try {
                entrada = new DataInputStream(socket.getInputStream());
                salida = new DataOutputStream(socket.getOutputStream());
                nombreUsuario = entrada.readUTF();
                System.out.println(nombreUsuario + " se ha conectado.");
                broadcastMessage(nombreUsuario + " se ha unido al chat.", this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void enviarMensaje(String mensaje) {
            try {
                salida.writeUTF(mensaje);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String mensaje = entrada.readUTF();
                    if (mensaje.equalsIgnoreCase("Salir")) {
                        System.out.println(nombreUsuario + " se ha desconectado.");
                        broadcastMessage(nombreUsuario + " ha salido del chat.", this);
                        socket.close();
                        break;
                    } else {
                        System.out.println(nombreUsuario + ": " + mensaje);
                        broadcastMessage(nombreUsuario + ": " + mensaje, this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}