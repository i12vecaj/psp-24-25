package Ejercicio2;


import javax.xml.crypto.Data;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

// hilo cliente para recibir información
public class Cliente implements Runnable{

    int puerto;

    public Cliente(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        DatagramSocket socket = null;

        try{
            socket = new DatagramSocket(puerto);

            byte[] bufer = new byte[1024];
            DatagramPacket entrada = new DatagramPacket(bufer, bufer.length);
            socket.receive(entrada);

            ByteArrayInputStream bais = new ByteArrayInputStream(entrada.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Notificacion noti = (Notificacion) ois.readObject();
            ois.close();
            System.out.println(noti.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
