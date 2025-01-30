import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(12345)) { //Creamos un servidor que escucha por el puerto 12345
            System.out.println("Esperando conexion...");

            try(Socket socket = serverSocket.accept(); //Aceptamos la conexion del cliente
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream()); //Creamos un flujo de entrada para recibir objetos
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())){ //Creamos el flujo de salida para enviar objetos al cliente

                System.out.println("Cliente conectado de manera correcta...");

                Numeros numeros = (Numeros) in.readObject(); //Recibimos el objeto Numeros desde el cliente

                int numero = numeros.getNumero();
                if (numero < 0 ){ //Control de errores para ver si el numero es menor que 0
                    System.out.println("El numero debe ser mayor o igual a 0");
                    return;
                }

                long cuadrado = (long) numero*numero; //Calculamos el cuadrado del numero
                long cubo = (long) numero*numero*numero; //Calculamos el cubo del numero

                numeros.setCuadrado(cuadrado); //Asignamos el valor de cuadrado al objeto
                numeros.setCubo(cubo); //Asignamos el valor de cubo al objeto

                out.writeObject(numeros); //Enviamos el objeto Numeros al cliente con los valores actualizados
                System.out.println("Informacion enviada al cliente");

            }catch (Exception e){
                System.err.println("Error al procesar la solicitud del cliente: " + e.getMessage());
            }finally {
                try {
                    serverSocket.close();
                }catch (Exception e){
                    System.err.println("Error al cerrar el socket: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}