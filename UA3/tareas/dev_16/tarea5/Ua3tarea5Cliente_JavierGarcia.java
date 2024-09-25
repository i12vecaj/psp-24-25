
import java.util.Scanner;
import java.net.*;
import java.io.*;

public class Ua3tarea5Cliente_JavierGarcia {

    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner sca=new Scanner(System.in);
        Numeros Numeros;

        Socket socketCliente = new Socket("localhost", 5000);
        int numBase = sca.nextInt();
        long cuadrado=0;
        long cubo=0;

        PrintWriter envio = new PrintWriter(socketCliente.getOutputStream(), true);
        envio.println(numBase);

        System.out.println("Mensaje enviado");

        String menRecibido;
        String[] array = new String[2];

        BufferedReader recibo = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        menRecibido = recibo.readLine();

        array = menRecibido.split(" ");

        cuadrado = Long.parseLong(array[0]);
        cubo = Long.parseLong(array[1]);
        
        Numeros = new Numeros(numBase,cuadrado,cubo);
        
        System.out.println(Numeros.toString());

        socketCliente.close();
        
    }
    
}
