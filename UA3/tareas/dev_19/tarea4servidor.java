import java.io.*;
import java.net.*;

public class tarea4servidor {

	public static void main(String[] args) throws IOException {

		ServerSocket server = new ServerSocket(2000);

		Socket cliente = server.accept();

		BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		String mensaje_Cliente = in.readLine();

		System.out.println("Mensaje del Cliente: " + mensaje_Cliente);

		PrintWriter enviar = new PrintWriter(cliente.getOutputStream(), true);
		String respuestaServidor = mensaje_Cliente.toUpperCase();

		enviar.println(respuestaServidor);

		System.out.println("Respuesta del servidor: " + respuestaServidor);

		server.close();
	}

}
