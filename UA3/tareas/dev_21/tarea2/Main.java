import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Estamos esperando los cliente....");
        int contador = 0;
       while (contador < 2){
           int numeroPuerto = 6000;// Puerto
           ServerSocket servidor = new ServerSocket(numeroPuerto);
           Socket clienteConectado = servidor.accept();

           InputStream entrada = ((Socket) clienteConectado).getInputStream();
           DataInputStream flujoEntrada = new DataInputStream(entrada);

           System.out.println("Información cliente: \n" + flujoEntrada.readUTF());

           OutputStream salida = clienteConectado.getOutputStream();
           DataOutputStream flujoSalida = new DataOutputStream(salida);

           flujoSalida.writeUTF("Mensaje recibido");

           entrada.close();
           flujoEntrada.close();
           salida.close();
           flujoSalida.close();
           clienteConectado.close();
           servidor.close();

           contador++;
       }
    }
}