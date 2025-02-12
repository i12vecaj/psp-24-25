package examenua3.parte1.parte2;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteChat {
    private int id;
    private int puerto;

    public ClienteChat(int id, int puerto) {
        this.id = id;
        this.puerto = puerto;
    }

    public void iniciar() {
        String servidorIP = "localhost";
        int servidorPuerto = 9700;
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket socket = new DatagramSocket()) {
            System.out.println("Escribe un mensaje para conectarte al chat (Para salir escribe 'SALIR')");

            //Recibimos mensajes
            Thread recibirMensajes = new Thread(() -> {
                try {
                    while (true) {
                        byte[] buffer = new byte[1024];
                        DatagramPacket paqueteRespuesta = new DatagramPacket(buffer, buffer.length);
                        socket.receive(paqueteRespuesta);
                        String mensaje = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                        System.out.println("\nMensaje: " + mensaje + "\n> ");
                    }
                } catch (IOException e) {
                    System.out.println("Error recibiendo mensajes.");
                }
            });
            recibirMensajes.start();

            //Envio de mensajes
            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();

                String mensajeConID = "Cliente " + id + ": " + mensaje;
                byte[] bufferMensaje = mensajeConID.getBytes();
                DatagramPacket paquete = new DatagramPacket(bufferMensaje, bufferMensaje.length, InetAddress.getByName(servidorIP), servidorPuerto);
                socket.send(paquete);

                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Saliendo del chat...");
                    break;
                }
            }

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }

        scanner.close();
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java ClienteChat [id] [puerto]");
            return;
        }

        int id = Integer.parseInt(args[0]);
        int puerto = Integer.parseInt(args[1]);

        ClienteChat cliente = new ClienteChat(id, puerto);
        cliente.iniciar();
    }
}
