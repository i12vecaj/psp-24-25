package ClienteServidorUDP;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class cliente_Clase {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);

        DatagramSocket socket = null;



        int puerto = 3500;



        InetAddress local = InetAddress.getLocalHost();
        System.out.println("Introduce un numero entero.");
        int numeroEntero = scanner.nextInt();

        Numeros numeros = new Numeros(numeroEntero,numeroEntero,numeroEntero);

        socket = new DatagramSocket(2500);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(numeros);
        oos.close();

        byte[] datos = baos.toByteArray();

        DatagramPacket paqueteEnviar = new DatagramPacket(datos, datos.length, local, puerto);
        socket.send(paqueteEnviar);


        byte[] buffer = new byte[1024];

        DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteEntrada);

        ByteArrayInputStream bais = new ByteArrayInputStream(paqueteEntrada.getData());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Numeros numeroRecibido = (Numeros) ois.readObject();
        ois.close();

        System.out.println("Objeto enviado: Numero entero:  " + numeroRecibido.getEntero()+", Numero cuadrado: "+numeroRecibido.getCuadrado()+",Numero Cubo: "+numeroRecibido.getCubo());

        socket.close();


    }
}
