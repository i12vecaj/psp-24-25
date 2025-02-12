package Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente2 {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 60000);

        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

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

