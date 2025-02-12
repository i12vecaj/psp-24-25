package UA3EXEJ1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class ChatServer{

    //Es más conveniente utilizar TCP porque garantiza la entrega de los mensajes en el orden correcto y sin pérdidas,
    // lo cual es esencial para la comunicación en tiempo real de un chat,
    // y facilita la gestión de múltiples clientes al mantener conexiones más confiables.


    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(3307);
        System.out.println("Servidor iniciado en el puerto 3307");
        Socket cliente = servidor.accept();

        //  Streams de entrada y salida para comunicarse con el cliente
        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

        // Bucle para recibir y enviar mensajes
        String mensaje;
        while ((mensaje = entrada.readLine()) != null) {
            System.out.println("Empleado Jose David: " + mensaje);
            if (mensaje.equalsIgnoreCase("SALIR")) break;
            salida.println("Jefe de Fundecor: " + mensaje);
        }

        cliente.close();
        servidor.close();
    }
}
