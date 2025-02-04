import java.io.*;
import java.net.*;
import java.util.Scanner;

// ClienteUDP
public class ua3tarea5fr1 {
    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        Scanner scanner = new Scanner(System.in);

        try {
            clientSocket = new DatagramSocket();  // aqui creo el socket
            InetAddress serverAddress = InetAddress.getLocalHost();
            int serverPort = 9777;   // pongo el puerto del servidor

            System.out.print("Introdoce un numero mayor o igual a 0 ");
            int numero = scanner.nextInt();
            if (numero < 0) {
                System.out.println("error el numero introducido no es mayor o igua a 0");
                return;
            }

            Numeros numeros = new Numeros(numero);  // aqui creo el objeto de numero
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(numeros);  // lo covierto a bytes
            byte[] enviarDatos = byteArrayOutputStream.toByteArray();

            DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, serverAddress, serverPort);
            clientSocket.send(enviarPaquete);  // se envia el paquete al servidor

            byte[] recibirDatos = new byte[1024];
            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);
            clientSocket.receive(recibirPaquete);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(recibirPaquete.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Numeros resultado = (Numeros) objectInputStream.readObject();
            System.out.println("el resultado del servidor es...: " + resultado);  // muestro por pantalla el resultado
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ha habido un error en el cliente: " + e.getMessage());
        } finally {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        }
    }
}

