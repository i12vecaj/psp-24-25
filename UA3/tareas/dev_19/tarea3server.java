import java.io.*;
import java.net.*;

public class tarea3server {

    
    public static void main(String[] args) throws IOException{
       
        ServerSocket server = new ServerSocket(2000);
        
        Socket cliente = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter fuera = new PrintWriter(cliente.getOutputStream(), true);
        
        String mensaje_Cliente = in.readLine();
        String respuestaServidor = mensaje_Cliente.toUpperCase();
        
       fuera.println(respuestaServidor);
        
       cliente.close();
    }
    
}
