import java.io.*;
import java.net.*;

public class UA3_T2 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(6030)) {
            System.out.println("Servidor escuchando en el puerto 6030");

            for (int i = 0; i < 2; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort());
                System.out.println("Puertos - Local: " + clientSocket.getLocalPort() + ", Remoto: " + clientSocket.getPort());

                OutputStream out = clientSocket.getOutputStream();
                out.write("Conexion exitosa\n".getBytes());

                clientSocket.close();
            }

            System.out.println(" Cerrando el servidor.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class TCPClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 6030)) {
            System.out.println("Conectado al servidor en " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
            System.out.println("Puertos - Local: " + socket.getLocalPort() + ", Remoto: " + socket.getPort());

            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            System.out.println("Mensaje recibido del servidor: " + reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
