
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ua3tarea3Cliente_JavierGarcia {

    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        Scanner sca = new Scanner(System.in);
        
        //Creamos el Socket con un puerto cualquiera teniendo en cuenta que en el programa del server 
        //hay que poner el mismo puerto
        Socket socket = new Socket("localhost", 5000);
        
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        System.out.println("Escribe lo que desee:");
        String mensajeCliente = sca.nextLine();//Mensaje que introduce el cliente
        
        out.println(mensajeCliente);
        //Recibe la respuesta del servidor
        String respuestaServer = in.readLine();
        
        //Se imprime la respuesta del servidor
        System.out.println(respuestaServer);
        
        //Se cierra el socket
        socket.close();
    }
    
}
