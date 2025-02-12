import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        Scanner scanner = new Scanner(System.in);
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            System.out.print("Introduce un numero entero: ");
            int numero = scanner.nextInt();

            if (numero < 0) {
                System.out.println("El numero debe ser mayor o igual a 0.");
                return;
            }

            Numeros numeros = new Numeros(numero);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(numeros);
            byte[] sendData = baos.toByteArray();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros resultado = (Numeros) ois.readObject();

            System.out.println("Numero: " + resultado.getNumero());
            System.out.println("Cuadrado: " + resultado.getCuadrado());
            System.out.println("Cubo: " + resultado.getCubo());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}

