import java.io.*;
import java.net.*;
import java.util.Scanner;

public class tarea5_cliente {

    public static void main(String[] args) throws IOException {
        numeros numeros;
        long cuadrado = 0;
        long cubo = 0;
        Socket socketCliente = new Socket("localhost", 2000);

        Scanner entrada = new Scanner(System.in);
        System.out.println(" Digite un numero: ");
        int numerobase = entrada.nextInt();

        PrintWriter enviarMensaje = new PrintWriter(socketCliente.getOutputStream(), true);
        enviarMensaje.println(numerobase);
        System.out.println(" Mensaje enviado ");

        String mensajeRecibido;
        String[] array = new String[2];

        BufferedReader recibirMensaje = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
        mensajeRecibido = recibirMensaje.readLine();

        array = mensajeRecibido.split("");

        cuadrado = Long.parseLong(array[0]);
        cubo = Long.parseLong(array[1]);
        numeros = new numeros(numerobase, cuadrado, cubo);
        System.out.println(numeros.toString());

        socketCliente.close();
    }
}
