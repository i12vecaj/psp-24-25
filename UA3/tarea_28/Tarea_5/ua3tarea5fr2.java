import java.io.*;
import java.net.*;
// SERVIDOR UDP
public class ua3tarea5fr2 {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;

        try {
            serverSocket = new DatagramSocket(9777);  // creo el socket con el puerto 9777
            System.out.println("esperendo el mensaje del servidor...");

            byte[] recibirDatos = new byte[1024];
            DatagramPacket recibirPaquete = new DatagramPacket(recibirDatos, recibirDatos.length);

            serverSocket.receive(recibirPaquete);  // aqui se espera y recibe el paquete del cliente

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(recibirPaquete.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Numeros numeros = (Numeros) objectInputStream.readObject();

            int numero = numeros.getNumero();
            if (numero < 0) {  // si pone un numero menor que 0 le muestro un mensaje de error
                System.out.println("el numero introducido no puede ser negativo.");
                return;
            }


            numeros.setCuadrado((long) numero * numero);
            numeros.setCubo((long) numero * numero * numero);

            //aqui obtengo el puerto y la direccion ip del cliente
            InetAddress clientAddress = recibirPaquete.getAddress();
            int clientPort = recibirPaquete.getPort();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(numeros);
            byte[] enviarDatos = byteArrayOutputStream.toByteArray();
            DatagramPacket enviarPaquete = new DatagramPacket(enviarDatos, enviarDatos.length, clientAddress, clientPort);
            serverSocket.send(enviarPaquete);

            System.out.println("El calculo ha sido correctamente calculado y enviado: " + numeros);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ha habido un error en el servidor : " + e.getMessage());
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}

