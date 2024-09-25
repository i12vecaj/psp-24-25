
import java.io.*;
import java.net.*;

public class Ua3tarea3Server_JavierGarcia {

    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
         //Creamos el Socket con un puerto cualquiera teniendo en cuenta que en el programa del cliente 
        //hay que poner el mismo puerto
        ServerSocket server = new ServerSocket(5000);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        //Recibe el mensaje del cliente
        String mensajeCliente = in.readLine();
        //La respuesta del server seria el mensaje del cliente en mayusculas
        String respuestaServer = mensajeCliente.toUpperCase();
        
        //Imprimimos la respuesta del servidor
        out.println(respuestaServer);
        
        //Se cierra el socket
        socket.close();
    }
    
}
