
import java.io.*;
import java.net.*;

public class Ua3tarea5Servidor_JavierGarcia {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ServerSocket serverSocket = new ServerSocket(5000);

        Socket socketCliente = serverSocket.accept();

        BufferedReader recibido = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        int numBase = Integer.parseInt(recibido.readLine());
        System.out.println("Numero recibido del cliente: " + numBase);

        long cuadrado;
        long cubo;
        cuadrado = numBase * numBase;
        cubo = cuadrado * numBase;

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        enviar.println(cuadrado + " " + cubo);

        System.out.println("Cuadrado del numero introducido por el cliente: " + cuadrado);
        System.out.println("Cubo del numero introducido por el cliente: " + cubo);

        socketCliente.close();
    }
    
}
