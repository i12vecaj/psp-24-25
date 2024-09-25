
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Ua3tarea4Cliente_JavierGarcia {

    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        Scanner sca = new Scanner(System.in);
        
        Socket socket = new Socket("localhost", 5000);
        
        boolean finalizador = false;
        String letra = "";
        String mensajeEnviado = "";
        String mensajeCliente = "";
        
        System.out.println("Introduce el mensaje:");
        
        while(!letra.equals("*")){
            letra = sca.next();
            if(!letra.equals("*")){
                mensajeCliente += letra;
            }
        }

        PrintWriter envio = new PrintWriter(socket.getOutputStream(), true);
        envio.println(mensajeCliente);

        System.out.println("Mensaje enviado");

        BufferedReader recibo = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String mensajeServidor = recibo.readLine();

        System.out.println("Mensaje de Servidor: " + mensajeServidor);

        socket.close();
    }
    
}
