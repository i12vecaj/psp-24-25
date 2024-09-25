import java.io.*;
import java.net.*;

public class tarea5_servidor {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2000);

        System.out.println("Escuchando en el puerto 2000");

        Socket socketCliente = serverSocket.accept();

        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        int numerobase = Integer.parseInt(recibirMensaje.readLine());
        System.out.println("Numero recibido: " + numerobase);

        long cuadrado;
        long cubo;

        cuadrado = numerobase * numerobase;
        cubo = cuadrado * numerobase;

        PrintWriter enviarNumero = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarNumero.println(cuadrado + "" + cubo);

        System.out.println("Cuadrado del numero: " + cuadrado);
        System.out.println("Cubo del numero: " + cubo);

        socketCliente.close();

    }

}
