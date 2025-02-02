package dev_20.ua3tarea5;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Servidor {
    public static void main(String[] args) {

        DatagramSocket socket = null;

        try{
            socket = new DatagramSocket(8080);

            byte[] bufer = new byte[1024];

            DatagramPacket entrada = new DatagramPacket(bufer, bufer.length);

            socket.receive(entrada);

            ByteArrayInputStream bais  = new ByteArrayInputStream(entrada.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros numeroRecibido = (Numeros) ois.readObject();
            ois.close();

            double cubo = Math.pow(numeroRecibido.entero, 3);
            double cuadrado = Math.pow(numeroRecibido.entero, 2);

            numeroRecibido.setCubo(Math.round(cubo));
            numeroRecibido.setCuadrado(Math.round(cuadrado));


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos= new ObjectOutputStream(baos);
            oos.writeObject(numeroRecibido);
            oos.close();

            byte[] objeto = baos.toByteArray();
            DatagramPacket salida = new DatagramPacket(objeto, objeto.length, InetAddress.getLocalHost(), 8081);
            socket.send(salida);

            socket.close();

        }catch (Exception ex){

            System.out.println("ERROR : " + ex);

        } finally {
            if(socket != null){
                socket.close();
            }
        }

    }
}
