package dev_20.ua3tarea5;

import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        DatagramSocket socket = null;
        try{
            int numero = scann.nextInt();

            if(numero <= 0){
                throw new Exception("El numero debe ser mayor a 0");
            }

            Numeros objeto = new Numeros(numero);

            socket = new DatagramSocket(8081);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(objeto);
            oos.close();

            byte[] datos = baos.toByteArray();
            DatagramPacket salida = new DatagramPacket(datos, datos.length, InetAddress.getLocalHost(), 8080);
            socket.setSoTimeout(10000);
            socket.send(salida);

            byte[] bufer = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(bufer, bufer.length);
            socket.receive(entrada);

            ByteArrayInputStream bais = new ByteArrayInputStream(entrada.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros numeros = (Numeros) ois.readObject();
            ois.close();

            System.out.println(numeros.toString());


        }catch (Exception ex){
            if(ex instanceof java.net.SocketTimeoutException){
                System.out.println("Se ha agotado el tiempo de espera, revise o contacte con el encargado del servidor");
            }else{
                System.out.println("Error: " + ex);
            }
        }finally {
            if(socket != null){
                socket.close();
            }
        }
    }
}
