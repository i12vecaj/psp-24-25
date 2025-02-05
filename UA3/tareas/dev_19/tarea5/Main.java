import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese un número entero: ");
        int num = sc.nextInt();

        if (num < 0) {
            System.out.println("El número debe ser mayor o igual a 0.");
            return;
        }

        Numeros numeros = new Numeros(num);

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress direccionServidor = InetAddress.getByName("localhost");
            int puertoServidor = 9000;

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(numeros);
            oos.flush();

            byte[] datosEnviados = baos.toByteArray();
            DatagramPacket paqueteEnviado = new DatagramPacket(datosEnviados, datosEnviados.length, direccionServidor, puertoServidor);
            socket.send(paqueteEnviado);

            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);

            ByteArrayInputStream bais = new ByteArrayInputStream(paqueteRecibido.getData());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Numeros numerosRecibidos = (Numeros) ois.readObject();

            System.out.println("Resultado recibido del servidor: " + numerosRecibidos);
        } catch (UnknownHostException e) {
            System.out.println("No se pudo encontrar el servidor.");
        } catch (SocketException e) {
            System.out.println("Error en el socket.");
        } catch (IOException e) {
            System.out.println("Error de Entrada/Salida.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al leer el objeto recibido.");
        }
    }
}
