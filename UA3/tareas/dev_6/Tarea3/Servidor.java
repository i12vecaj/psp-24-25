import java.io.*;
import java.net.*;


public class Servidor {
    public static void main(String[] args) {
        int puerto = 5000;
        try(ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor esperando conexión en el puerto " + puerto + "...");

            Socket socket = servidorSocket.accept();
            System.out.println("Cliente conectado");

            //Recibir mensaje del cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Enviar respuesta al cliente
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            //Leer mensaje del cliente
            String mensajeRecibido = entrada.readLine();
            System.out.println("Mensaje recibido: " + mensajeRecibido);

            // COnvertir mensaje a mayusculas
            String mensajeMayusculas = mensajeRecibido.toUpperCase();

            //Enviar mensaje convertido al cliente
            salida.print(mensajeMayusculas);
            System.out.println("mensaje encisado en mayúsculas: " + mensajeMayusculas);

            //Cerrar recursos
            socket.close();

        } catch (IOException e){
            System.out.println("Error en el servidor: "+ e.getMessage());


        }
    }
}