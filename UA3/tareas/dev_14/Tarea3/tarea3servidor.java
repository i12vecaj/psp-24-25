import java.io.*;
import java.net.*;

public class tarea3servidor {
 public static void main(String[] args) throws IOException
 {

    ServerSocket serversocket = new ServerSocket(9999);
    Socket socket = serversocket.accept();
    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
    String texto= in.readLine();
    String devuelto = texto.toUpperCase();
    out.println(devuelto);
    socket.close();
 }    

}
