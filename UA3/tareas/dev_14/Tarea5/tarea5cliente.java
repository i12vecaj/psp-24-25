
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class tarea5cliente {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        tarea5numeros Num;
        int base;
        long cuadrado, cubo;
        Socket socketcliente = new Socket("localhost", 6000);

        System.out.println("Introduce un numero: ");
        base = scan.nextInt();

        PrintWriter enviar = new PrintWriter(socketcliente.getOutputStream(), true);
        enviar.println(base);
        System.out.println("Mensaje enviado");

        String recibido;
        String[] array = new String[2];

        BufferedReader recibir = new BufferedReader(new InputStreamReader(socketcliente.getInputStream()));
        recibido = recibir.readLine();
        array = recibido.split(" ");
        cuadrado = Long.parseLong(array[0]);
        cubo = Long.parseLong(array[1]);

        Num = new tarea5numeros(base, cuadrado, cubo);

        System.out.println(Num.toString());

        socketcliente.close();

    }
}
