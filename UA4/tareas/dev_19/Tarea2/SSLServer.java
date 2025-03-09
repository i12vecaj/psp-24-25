import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.security.KeyStore;

public class SSLServer {
    public static void main(String[] args) throws Exception {
        // Cargar el almacén de claves
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(new FileInputStream("servidor.keystore"), "password".toCharArray());

        // Inicializar el KeyManagerFactory
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(keyStore, "password".toCharArray());

        // Crear el SSLServerSocket
        SSLServerSocketFactory sslServerSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(8443);

        System.out.println("Servidor SSL iniciado y esperando conexiones...");

        // Aceptar conexiones de clientes
        SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

        // Leer datos del cliente
        BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        String mensaje = reader.readLine();
        System.out.println("Mensaje recibido del cliente: " + mensaje);

        // Enviar respuesta al cliente
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
        writer.write("Hola desde el servidor SSL\n");
        writer.flush();

        // Cerrar conexiones
        reader.close();
        writer.close();
        sslSocket.close();
        sslServerSocket.close();
    }
}
