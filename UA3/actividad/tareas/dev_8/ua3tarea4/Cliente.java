import java.net.*;
            import java.io.*;
            import java.util.Scanner;

            public class Cliente {

                public static void main(String[] args) {
                    DatagramSocket socket = null;
                    Scanner entradaUsuario = null;

                    try {
                        socket = new DatagramSocket();
                        socket.setSoTimeout(3000);
                        InetAddress direccionServidor = InetAddress.getByName("localhost");
                        entradaUsuario = new Scanner(System.in);

                        String mensaje;
                        while (true) {
                            System.out.print("Ingrese un mensaje (o * para salir): ");
                            mensaje = entradaUsuario.nextLine();

                            byte[] bufferEnvio = mensaje.getBytes();
                            DatagramPacket paqueteEnvio = new DatagramPacket(bufferEnvio, bufferEnvio.length, direccionServidor, 9876);
                            socket.send(paqueteEnvio);

                            if (mensaje.equals("*")) {
                                break;
                            }

                            byte[] bufferRecepcion = new byte[1024];
                            DatagramPacket paqueteRecepcion = new DatagramPacket(bufferRecepcion, bufferRecepcion.length);

                            try {
                                socket.receive(paqueteRecepcion);
                                String respuesta = new String(paqueteRecepcion.getData(), 0, paqueteRecepcion.getLength());
                                System.out.println("Respuesta del servidor: " + respuesta);
                            } catch (SocketTimeoutException e) {
                                System.out.println("Tiempo de espera agotado. Paquete perdido.");
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (socket != null && !socket.isClosed()) {
                            socket.close();
                        }
                        if (entradaUsuario != null) {
                            entradaUsuario.close();
                        }
                    }
                }
            }