import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Mensaje: ");
        out.println(userInput.readLine());
        System.out.println("Respuesta: " + in.readLine());

        socket.close();
    }
}
