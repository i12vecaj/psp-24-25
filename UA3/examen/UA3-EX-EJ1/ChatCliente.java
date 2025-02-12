import java.io.*;
import java.net.*;
// Nombre: Alejandro Sanchez Quesada
// Respuesta a mi solucion de lo que yo creo  en este caso mejor usa el TCP:
// Ya que tiene un orden de los mensaje y te garantiza que te va llegar los mensaje , porque en el caso de udp no tiene un orden de mensajes y encima lo mas importante que no llega con seguridad el mensaje.
public class ChatCliente {
    public static void main(String[] args) throws IOException {
        String host = "localhost";  // aqui pongo la direccion del servidor
        int puerto = 12345;  // aqui indico el puerto del servidor

        // conectamos al servidor
        Socket socket = new Socket(host, puerto);
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));


        System.out.print("Porfavor indica el nombtre: "); //aqui le pido el nombre al cliente
        String nombre = consola.readLine();


        new Thread(() -> { // aqui hago un hilo para que pueda recibir lo mensaje del servidor
            try {
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    System.out.println(mensaje);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        String mensaje;
        while (true) {
            mensaje = consola.readLine();
            if (mensaje.equalsIgnoreCase("salir")) {
                salida.println(nombre + " ha dejado el chat.");
                break;  // para que pueda salir del chat
            }
            salida.println(nombre + ": " + mensaje);  // esto lo hago para que pueda enviar un mensaje con su nombre
        }

        socket.close();  // cierro socket y se cierra la conexion con el servidor
        System.out.println("conexion se a cerrado");
    }
}
