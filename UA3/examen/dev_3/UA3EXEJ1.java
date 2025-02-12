package UA3.examen.dev_3;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//He elegido TCP ya que establece una conexion mas segura , asegura la entrega de paquetes y el envio de orden estos 
//mientras que UDP es todo lo contrario ,este solo se centra en la eficacia mas que en la seguridad.
public class UA3EXEJ1 {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (
                //establezco conexion con el servidor.
                Socket socket = new Socket(SERVIDOR_IP, PUERTO);
                DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
                DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor");

            // Recibo un mensaje del servidor que nos pide el nombre tras conectarnos.
            System.out.print(flujoEntrada.readUTF() + " ");
            //Escribo el nombre y lo guardo en el flujo de salida.
            String nombre = teclado.readLine();
            flujoSalida.writeUTF(nombre);

            // Hilo que nos permite recibir mensajes del servidor
            Thread recibirMensajes = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = flujoEntrada.readUTF()) != null) {
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("No estas en el servidor.");
                }
            });

            recibirMensajes.start();

            // El while nos permite enviar mensajes al servidor
            String mensaje;
            while (true) {
                mensaje = teclado.readLine();
                flujoSalida.writeUTF(mensaje);
                //En caso de que el mendsaje sea Salir esto cerrara el programa para ese cliente
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Saliendo del chat...");
                    break;
                }
            }
            //cierro conexion con los sockets
            socket.close();
        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        }
    }
}


class Servidor {
    public static void main(String[] args) throws IOException {
        int numeroPuerto = 6000;
        ServerSocket servidor = new ServerSocket(numeroPuerto);
        System.out.println("Esperando a que se conecten los clientes...");
        List<DataOutputStream> clientes = Collections.synchronizedList(new ArrayList<>());
        //establezco la conexion con el cliente
        while (true) {
            Socket clienteConectado = servidor.accept();
            System.out.println("Nuevo cliente conectado: " + clienteConectado.getInetAddress());

            DataOutputStream flujoSalida = new DataOutputStream(clienteConectado.getOutputStream());
            clientes.add(flujoSalida);

            new Thread(() -> manejarCliente(clienteConectado, clientes)).start();
        }
    }

    private static void manejarCliente(Socket cliente, List<DataOutputStream> clientes) {
        try (
                DataInputStream flujoEntrada = new DataInputStream(cliente.getInputStream());
                DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream())
        ) {
            flujoSalida.writeUTF("Introduce tu nombre:");
            String nombre = flujoEntrada.readUTF();
            System.out.println(nombre + " se ha unido al chat.");

            enviarATodos(nombre + " se ha unido al chat.", clientes);

            String mensaje;
            while ((mensaje = flujoEntrada.readUTF()) != null) {
                if (mensaje.equalsIgnoreCase("SALIR")) break;
                String mensajeConNombre = nombre + ": " + mensaje;
                System.out.println(mensajeConNombre);
                enviarATodos(mensajeConNombre, clientes);
            }

            System.out.println(nombre + " ha salido del chat.");
            enviarATodos(nombre + " ha salido del chat.", clientes);
            clientes.remove(flujoSalida);
            cliente.close();

        } catch (IOException e) {
            System.out.println("Error con un cliente: " + e.getMessage());
        }
    }

    private static void enviarATodos(String mensaje, List<DataOutputStream> clientes) {
        synchronized (clientes) {
            for (DataOutputStream cliente : clientes) {
                try {
                    cliente.writeUTF(mensaje);
                } catch (IOException e) {
                    System.out.println("Error enviando mensaje: " + e.getMessage());
                }
            }
        }
    }
}

class Cliente1 {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (
                //establezco conexion con el servidor.
                Socket socket = new Socket(SERVIDOR_IP, PUERTO);
                DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
                DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor");

            // Recibo un mensaje del servidor que nos pide el nombre tras conectarnos.
            System.out.print(flujoEntrada.readUTF() + " ");
            //Escribo el nombre y lo guardo en el flujo de salida.
            String nombre = teclado.readLine();
            flujoSalida.writeUTF(nombre);

            // Hilo que nos permite recibir mensajes del servidor
            Thread recibirMensajes = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = flujoEntrada.readUTF()) != null) {
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("No estas en el servidor.");
                }
            });

            recibirMensajes.start();

            // El while nos permite enviar mensajes al servidor
            String mensaje;
            while (true) {
                mensaje = teclado.readLine();
                flujoSalida.writeUTF(mensaje);
                //En caso de que el mendsaje sea Salir esto cerrara el programa para ese cliente
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Saliendo del chat...");
                    break;
                }
            }
            //cierro conexion con los sockets
            socket.close();
        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        }
    }
}

class Cliente2 {
    private static final String SERVIDOR_IP = "localhost";
    private static final int PUERTO = 6000;

    public static void main(String[] args) {
        try (
                //establezco conexion con el servidor.
                Socket socket = new Socket(SERVIDOR_IP, PUERTO);
                DataInputStream flujoEntrada = new DataInputStream(socket.getInputStream());
                DataOutputStream flujoSalida = new DataOutputStream(socket.getOutputStream());
                BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Conectado al servidor");

            // Recibo un mensaje del servidor que nos pide el nombre tras conectarnos.
            System.out.print(flujoEntrada.readUTF() + " ");
            //Escribo el nombre y lo guardo en el flujo de salida.
            String nombre = teclado.readLine();
            flujoSalida.writeUTF(nombre);

            // Hilo que nos permite recibir mensajes del servidor
            Thread recibirMensajes = new Thread(() -> {
                try {
                    String mensaje;
                    while ((mensaje = flujoEntrada.readUTF()) != null) {
                        System.out.println(mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("No estas en el servidor.");
                }
            });

            recibirMensajes.start();

            // El while nos permite enviar mensajes al servidor
            String mensaje;
            while (true) {
                mensaje = teclado.readLine();
                flujoSalida.writeUTF(mensaje);
                //En caso de que el mendsaje sea Salir esto cerrara el programa para ese cliente
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    System.out.println("Saliendo del chat...");
                    break;
                }
            }
            //cierro conexion con los sockets
            socket.close();
        } catch (IOException e) {
            System.err.println("Error en la conexión con el servidor: " + e.getMessage());
        }
    }
}