import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try(Socket socket = new Socket("localhost",12345);//Conectamos el cliente al servidor por el puerto
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); //Creamos un flujo para enviar los mensajes
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){ //Creamos un flujo para recibir los mensajes

            System.out.println("Introduzca un numero entero:");
            int numero = scanner.nextInt();

            if(numero < 0){
                System.out.println("El numero debe ser mayo o igual a 0.");
            }

            Numeros numeros = new Numeros(numero); //Creamos el objeto numero de la clase Numeros

            //Enviamos el objeto al servidor
            out.writeObject(numeros); //Recibimos el objeto con los cálculos del servidor

            // Recibir el objeto con los resultados
            Numeros resultado = (Numeros) in.readObject();//Recibimos el objeto con los cálculos del servidor

            System.out.println("Numero: "+ resultado.getNumero());
            System.out.println("Cuadrado: "+ resultado.getCuadrado());
            System.out.println("Cubo: "+ resultado.getCubo());

        }catch(UnknownHostException e) {  //Prueba de errores por si el servidor no se encuentra
            System.err.println("Servidor no encontrado: " + e.getMessage());//Mensaje de error
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}