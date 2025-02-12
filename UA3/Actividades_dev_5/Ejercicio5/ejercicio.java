import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.io.Serializable;

class Numeros implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    public Numeros() {}

    public Numeros(int numero) {
        this.numero = numero;
        this.cuadrado = 0;
        this.cubo = 0;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }
}

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP escuchando en el puerto " + PORT);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(receivePacket);
                
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numeros numeros = (Numeros) ois.readObject();
                
                System.out.println("Número recibido: " + numeros.getNumero());
                if (numeros.getNumero() < 0) {
                    System.out.println("Número inválido, ignorando...");
                    continue;
                }
                
                numeros.setCuadrado((long) Math.pow(numeros.getNumero(), 2));
                numeros.setCubo((long) Math.pow(numeros.getNumero(), 3));
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                byte[] sendData = baos.toByteArray();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}

class UDPClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "127.0.0.1";
        final int SERVER_PORT = 12345;
        Scanner scanner = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            clientSocket.setSoTimeout(5000);
            System.out.println("Ingrese un número entero (negativo para salir): ");
            
            while (true) {
                System.out.print("Número: ");
                int num = scanner.nextInt();
                if (num < 0) break;
                
                Numeros numeros = new Numeros(num);
                
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numeros);
                byte[] sendData = baos.toByteArray();
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(SERVER_ADDRESS), SERVER_PORT);
                clientSocket.send(sendPacket);
                
                byte[] buffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
                try {
                    clientSocket.receive(receivePacket);
                    ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    Numeros responseNumeros = (Numeros) ois.readObject();
                    
                    System.out.println("Número: " + responseNumeros.getNumero());
                    System.out.println("Cuadrado: " + responseNumeros.getCuadrado());
                    System.out.println("Cubo: " + responseNumeros.getCubo());
                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado. El servidor no respondió.");
                }
            }
            System.out.println("Cliente cerrado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}
