

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class tarea3cliente {

    public static void main(String[] args)  throws IOException{
        
        

        Socket socket = new Socket("localhost",9999);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner scan = new Scanner(System.in);
        String texto;
        String devuelto;
        System.out.println("Introducir mensaje: ");
        texto = scan.nextLine();
        out.println(texto);
        devuelto = in.readLine();
        System.out.println(devuelto);
        socket.close();
        

    }

}
