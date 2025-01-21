import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        final int PUERTO = 5000; // Puerto donde escucha el servidor

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor esperando conexiones en el puerto " + PUERTO);

            // Aceptar primer cliente
            Socket cliente1 = servidor.accept();
            System.out.println("Cliente 1 conectado");
            System.out.println("Puerto local del servidor: " + cliente1.getLocalPort());
            System.out.println("Puerto remoto del cliente: " + cliente1.getPort());

            // Aceptar segundo cliente
            Socket cliente2 = servidor.accept();
            System.out.println("Cliente 2 conectado");
            System.out.println("Puerto local del servidor: " + cliente2.getLocalPort());
            System.out.println("Puerto remoto del cliente: " + cliente2.getPort());

            // Cerrar conexiones
            cliente1.close();
            cliente2.close();
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}



