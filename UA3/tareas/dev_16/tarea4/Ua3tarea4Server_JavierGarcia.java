
import java.io.*;
import java.net.*;

public class Ua3tarea4Server_JavierGarcia {

    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        ServerSocket socket = new ServerSocket(5000);

        Socket socketCliente = socket.accept();

        BufferedReader recibo = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        String mensajeCliente = recibo.readLine();
        
        System.out.println("Mensaje del Cliente: " + mensajeCliente);

        PrintWriter enviar = new PrintWriter(socketCliente.getOutputStream(), true);
        String mensajeServidor = mensajeCliente.toUpperCase();
        enviar.println(mensajeServidor);

        System.out.println("Mensaje del Servidor: " + mensajeServidor);

        socket.close();
    }
    
}
