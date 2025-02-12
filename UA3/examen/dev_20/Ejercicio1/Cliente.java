package Ejercicio1;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente{
    public static void main(String[] args) throws IOException {
        // Genero el socket para el cliente
        Socket socket = new Socket("localhost", 60000);
        // utilizo las clases BufferedReadr y PrintWriter que aprendí con IA en la acctividad colaborativa
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));


        // genero un bucle para mandar y recibir mensajes
        String mensaje;
        while (true) {
            System.out.print("Tú: ");
            mensaje = teclado.readLine();
            salida.println(mensaje);

            if (mensaje.equalsIgnoreCase("SALIR")){
                break;
            }

            String respuesta = entrada.readLine();
            System.out.println(respuesta);
        }

        socket.close();
    }
}

