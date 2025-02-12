//Código hecho por Alberto Mármol Bello para la asignatura de Servicios y Procesos a fecha 12/02/2025.
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClienteChat {
    //Pongo una dirección del servidor.
    static final String SERVIDOR = "localhost"; 
    //Y el puerto del servidor.
    static final int PUERTO = 12345; 

    public static void main(String[] args) {
        try (
            //Aquí es donde establezco la conexión con el servidor.
            Socket socket = new Socket(SERVIDOR, PUERTO);
            //Añado un flujo de entrada para recibir mensajes del servidor.
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Y también tengo que poner el flujo de salida para enviar los mensajes al servidor.
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            //Un scanner para leer la entrada del usuario.
            Scanner scanner = new Scanner(System.in)
        ) {
            //Escribo una mensaje de bienvenida del servidor.
            System.out.println(entrada.readLine()); 
            //Leo el nombre del usuario que ha entrado al servidor.
            String nombre = scanner.nextLine(); 
            //Por último envio el nombre al servidor.
            salida.println(nombre); 
            
            //Creo un hilo para escuchar los mensajes del servidor y mostrarlos en la consola.
            Thread lector = new Thread(() -> {
                try {
                    String mensaje;
                    //Aquí es donde se leen los mensajes del servidor.
                    while ((mensaje = entrada.readLine()) != null) { 
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            lector.start();
            
            //Un bucle para enviar mensajes al servidor.
            String mensaje;
            while (!(mensaje = scanner.nextLine()).equalsIgnoreCase("SALIR")) {
                //Aquí se envia dicho mensaje al servidor.
                salida.println(mensaje);
            }
            //Por último notifico al servidor que el cliente se ha desconectado.
            salida.println("SALIR");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}