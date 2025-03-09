import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.*;

public class SSLClient {
    public static void main(String[] args) throws Exception {
        // Crear el SSLSocket
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 8443);

        // Enviar datos al servidor
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(sslSocket.getOutputStream()));
        writer.write("Hola desde el cliente SSL\n");
        writer.flush();

        // Leer respuesta del servidor
        BufferedReader reader = new BufferedReader(new InputStreamReader(sslSocket.getInputStream()));
        String mensaje = reader.readLine();
        System.out.println("Mensaje recibido del servidor: " + mensaje);

        // Cerrar conexiones
        reader.close();
        writer.close();
        sslSocket.close();
    }
}
