package ClienteServidorUDP;

import Actividad_Cliente_Servidor_Token.Producto;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorNumeros {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket socket = new DatagramSocket(3500);
        InetAddress local = InetAddress.getLocalHost();

        byte[] buffer = new byte[1024];

        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

        socket.receive(paquete);

        ByteArrayInputStream bais = new ByteArrayInputStream(paquete.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Numeros numeroRecibido = (Numeros) ois.readObject();
        ois.close();

        System.out.println("Numero Entero:" + numeroRecibido.getEntero()+", Numero largo 1: "+numeroRecibido.getCuadrado()+", Numero largo 2: "+numeroRecibido.getCubo());


        int numeroCuadrado = (int) Math.pow(numeroRecibido.getEntero(),2);

        int numeroCubo = (int) Math.pow(numeroRecibido.getCubo(),3);


        numeroRecibido.setCuadrado(numeroCuadrado);
        numeroRecibido.setCubo(numeroCubo);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(numeroRecibido);
        oos.close();

        byte[] datos = baos.toByteArray();

        DatagramPacket paqueteEnvio = new DatagramPacket(datos, datos.length, local, 2500);
        socket.send(paqueteEnvio);

        System.out.println("Paquete enviado");


        socket.close();


    }
}
