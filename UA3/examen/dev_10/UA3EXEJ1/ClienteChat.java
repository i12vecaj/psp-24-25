//Revisado por JD: 17/02/25

package UA3EXEJ1;
import java.io.*;
import java.net.*;
public class ClienteChat {

    //Es más conveniente utilizar TCP porque garantiza la entrega de los mensajes en el orden correcto y sin pérdidas,
    // lo cual es esencial para la comunicación en tiempo real de un chat,
    // y facilita la gestión de múltiples clientes al mantener conexiones más confiables.


    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 3307);

        // BufferedReader y PrintWriter para leer y enviar del servidor, respectivamente
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        // Bucle de comunicación con el servidor infinito en principio hasta que el usuario quiera SALIR escribiendo eso mismo
        String mensaje;
        while (true) {
            System.out.print("Empleado Jose David: ");
            mensaje = teclado.readLine();
            salida.println(mensaje);

            if (mensaje.equalsIgnoreCase("SALIR"))
                break;
            String respuesta = entrada.readLine();
            System.out.println(respuesta);
        }

        socket.close();
    }
}


