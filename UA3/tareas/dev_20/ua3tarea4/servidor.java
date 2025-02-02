package dev_20.ua3tarea4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class servidor {
    public static void main(String[] argv) throws Exception {
        byte[] bufer = new byte[1024];
        DatagramSocket socket = null;
        try{
            // Defino el socket
            socket = new DatagramSocket(60000);
            // Creo el datagram para recibir datos y espero una peticion
            DatagramPacket entrada = new DatagramPacket(bufer, bufer.length);
           while (true){
               socket.receive(entrada);
               // Recibo los datos y los formateo informando al usuario
               String datosRecibidos = new String(entrada.getData(), 0 , entrada.getLength());
                if(datosRecibidos.equals("*")){

                    break;
                }
               System.out.println("Se ha recibido: " + datosRecibidos);

               String datosEnMayus = datosRecibidos.toUpperCase();

               byte[] datosFormateados;

               datosFormateados = datosEnMayus.getBytes();

               System.out.println("Se envia: " + datosEnMayus);
               // Creo el datagram de salida y envio la respuesta
               DatagramPacket salida = new DatagramPacket(datosFormateados, datosFormateados.length, InetAddress.getLocalHost(), 60001);

               socket.send(salida);
           }

        }finally {
            if(socket != null){
                socket.close();

            }
        }
    }
}
