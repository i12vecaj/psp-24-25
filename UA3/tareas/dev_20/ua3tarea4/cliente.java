package dev_20.ua3tarea4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class cliente {
    public static void main(String[] argv) throws Exception {
        DatagramSocket socket = null;
        try{
            Scanner scann = new Scanner(System.in);
             socket = new DatagramSocket(60001);
            while (true){
                System.out.print("Escriba algo: ");
                String datos = scann.nextLine();


                byte[] datosFormateados = datos.getBytes();

                DatagramPacket envio = new DatagramPacket(datosFormateados, datosFormateados.length, InetAddress.getLocalHost(), 60000);

                socket.send(envio);

                if(datos.equals("*")){
                    scann.close();
                    break;
                }

                byte[] bufer = new byte[1024];

                DatagramPacket recibo = new DatagramPacket(bufer, bufer.length);

                socket.receive(recibo);

                String respuesta = new String(recibo.getData(), 0 , recibo.getLength());

                System.out.println("Datos recibidos: " + respuesta);
            }
        }finally {
            if(socket != null){
                socket.close();
            }
        }

    }
}
