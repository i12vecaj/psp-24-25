package Ejercicio2;

/*
* En este caso es más conveniente usar udp, ya que es primordial la velocidad sin necesidad de conexión persistenete
*
* En este caso he cumplido con casi todos los requisitos excepto recibir la alerta externa que no entiendo muy bien como recibirla si el servidor no espera recibir datos
* */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static void main(String[] args) {

        DatagramSocket socket = null;
        List<Integer> listaPuertos = new ArrayList<>();

        try{
            // creo el socket servidor
            socket = new DatagramSocket(8080);

            Notificacion notificacion = new Notificacion("Soy la notificación");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos= new ObjectOutputStream(baos);
            oos.writeObject(notificacion);
            oos.close();

            for(int i = 0; i < 2 ; i ++){
                listaPuertos.add(8000+i); // Genera los puertos 8000 y 8001
            }
            // mando el mensaje a todos los puerros
           for(Integer cliente: listaPuertos){
               byte[] objeto = baos.toByteArray();
               DatagramPacket salida = new DatagramPacket(objeto, objeto.length, InetAddress.getLocalHost(), cliente);
               socket.send(salida);
           }

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
