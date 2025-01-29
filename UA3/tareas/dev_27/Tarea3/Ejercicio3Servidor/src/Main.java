import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(12345);
        Socket socket = server.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(in.readLine().toUpperCase());
        socket.close();
        server.close();
    }
}
