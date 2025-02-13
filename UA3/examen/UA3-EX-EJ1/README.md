/*****************************************************
 *                   REVISIÓN EXAMEN                 *
 *---------------------------------------------------*
 * Fecha revisión: 13/02/2025                                  *
 *---------------------------------------------------*
 * Observaciones:                                   *
 * Dani, enhorabuena por tus soluciones. Lo has hecho genial, sigue así. *
 *                                                 *
 *                                                 *
 *****************************************************/

# Ejercicio 1

This exercise simulates a real-time chat with 3 clients. Each client consists of a ChatBox, a Send button and 
a screen that serves as a message displayer.

## Usage

PLEASE execute the Server class in order for the clients to work. If you want to stop the server,
type SALIR and hit send.

After this, execute the number of clients you wish to make the interaction with, up to 3.
Executing the Clients class is what will make the main Windows appear.
Console on Server class shows every message sent by the clients.
If a Client is executed after messages have been sent by other previously executed clients,
these sent messages will NOT appear in the new Client.


```java
import java.io.*;
import java.net.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {


    //el uso de variables finales asegura que dicha variable sea constante durante el resto del código.

    private static final int PORT = 1234;
    //arraylist con el control de los clientes
    private static CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();


    public static void main(String[] args) {
        System.out.println("Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {       /*este fragmento de código acepta al cliente seleccionado
                                    está situado dentro de un try() catch() para manejo de excepciones.
                                    el server en espera acepta a los clientes mediante dicho código */
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

## Contributing

Just adding this to stay on path with the documentation.

TCP was chosen for this particular exercise due to the need for every package to arrive to its destination.
In this case, security of information is prioritized over speed. Output from the clients must also be
received in order by the previously executed server.

