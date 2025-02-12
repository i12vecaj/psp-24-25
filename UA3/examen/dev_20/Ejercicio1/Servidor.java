package Ejercicio1;

/*
    Ejercicio 1: Chat Simple Cliente-Servidor
    Se desea implementar una aplicación de chat entre un cliente y un servidor. El servidor debe estar activo constantemente y poder manejar múltiples clientes al mismo tiempo.
    Cada cliente podrá enviar mensajes al servidor, y este los reenviará a todos los clientes conectados.
    Requisitos:
    El servidor debe estar disponible de forma continua y gestionar múltiples clientes.
    Los clientes pueden enviar mensajes y recibir respuestas del servidor en tiempo real.
    Cada mensaje debe ir acompañado del nombre del remitente.
    El servidor debe mostrar en consola todos los mensajes recibidos.
    El cliente podrá salir enviando un comando especial como "SALIR".
* */

/*
* En este caso veo correcto utilizar el modelo TCP/IP, ya que es esencial que el servidor y cliente estén conectados,
* además de que es importante evitar la pérdida de información en los sistemas de mensajería.
*
* No me ha dado tiempo a terminarlo y solo responde a 1, pero el funcionamiento que había pensado era guardar los clientes en
* una lista para tenerlos almacenados, una vez el servidor recibe el mensaje los demás que están en la lista recibirían
* el mensaje (los cuales los estarían esperando)
* */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static List<Socket> clientes;
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = null;
        clientes = new ArrayList<>();
        try{
            // Creo el socket del serviodr
            servidor = new ServerSocket(60000);
            System.out.println("Servidor iniciado en el puerto 12345");
            Socket cliente = servidor.accept(); // espero al cliente y recojo los datos
            clientes.add(cliente);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            String mensaje = entrada.readLine();
            System.out.println("Mensaje del cliente: " + mensaje);
            while (true) {
                // en caso de que escriba salir de cualquier forma saldrá
                if (mensaje.equalsIgnoreCase("SALIR")){
                    break;
                }
                //le envio la respuesta en caso de que no haya escrito salir
                salida.println("Servidor: " + mensaje);
            }

            cliente.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(servidor != null){
                servidor.close();
            }
        }
    }
}


