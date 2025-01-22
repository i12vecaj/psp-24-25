import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
public class Conexion {
    int puerto = 1234;
    String host = "localhost";
    String mensajeservidor;
    ServerSocket servidor;
    Socket cliente;
    DataOutputStream salidaServidor, salidaCliente;
    public Conexion (String tipo) throws IOException {
        if (tipo.equalsIgnoreCase("servidor")) {
            servidor = new ServerSocket(puerto);
            cliente = new Socket();
        } else {
            cliente = new Socket(host, puerto);
        }
    }
}

