package com.mycompany.tarea3;


import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class Main {
    public static void main(String[] args) {
        
        int puerto=8080;
        String host="localhost";
        
        Option opt1= Option.builder(null).argName("help")
                .longOpt("help")
                .hasArg(false)
                .required(false)
                .desc("Ayuda a los demás")
                .build();
        
        Option opt2= Option.builder("h").argName("hostname")
                .longOpt("hostname")
                .hasArg(true)
                .required(false)
                .desc("Indica el hostname")
                .build();
        
        Option opt3= Option.builder("p").argName("port")
                .longOpt("port")
                .hasArg(true)
                .required(false)
                .desc("Indica el puerto")
                .build();
        
        Option opt4= Option.builder("v").argName("version")
                .longOpt("version")
                .hasArg(false)
                .required(false)
                .desc("Indica la version")
                .build();
        
        Option opt5= Option.builder("c").argName("conf")
                .longOpt("conf")
                .hasArg(true)
                .required(false)
                .desc("Muestra la configuracion")
                .build();
        
        
        Options options = new Options();
        options.addOption(opt1).addOption(opt2).addOption(opt3).addOption(opt4).addOption(opt5);
        CommandLineParser linea = new DefaultParser();
        
        try{
            
            CommandLine cl = linea.parse(options, args);
            Option[] opciones = cl.getOptions();
            Map<String, Object> argumentos = new HashMap<String, Object>();
            
            for(Option opcion : opciones){
                argumentos.put(opcion.getArgName(), opcion.getValue());
            }
            
            if(argumentos.containsKey("help")){
                System.out.println("--help: muestra por salida estándar un mensaje de ayuda para el uso del programa.\n"
                        + "-h, --hostname: nombre de host/IP en el que la API escucha, es decir, donde proporcionamos el servicio.\n"
                        + "-p, --port: número de puerto específico en el que el hostname escucha y atiende peticiones.\n"
                        + "-v, --version: muestra por salida estándar la versión de la API.");
                System.exit(0);
            }else if(argumentos.containsKey("h") || argumentos.containsKey("hostname")){
                host = String.valueOf(argumentos.get("hostname"));
                System.out.println(host);
            }else if(argumentos.containsKey("p") || argumentos.containsKey("port")){
                puerto = Integer.parseInt(String.valueOf(argumentos.get("port")));
                System.out.println(puerto); 
            }else if(argumentos.containsKey("v") || argumentos.containsKey("version")){
                System.out.println("68.69.70");
                System.exit(0);
            }
            
            Api(host,puerto);
            
            
        }catch(Exception e){
            System.out.println(0);
        }
    }
        
    public static void Api (String hostname, int port) throws IOException{

          String welcomeMessage = "Hola muy buenass =) !!! desde nuestro framework-less REST API";
          String byebyeMessage = "Adios, te echare de menos =( !!! desde nuestro framework-less REST API";


            DataStorage store = new DataStorage();

            HttpServer server;
                try(final DatagramSocket socket = new DatagramSocket()){
                    socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
                    server= HttpServer.create(new InetSocketAddress(hostname, port), 0);
                } 

            server.createContext("/api/greeting", (exchange -> {

                if ("GET".equals(exchange.getRequestMethod())) {
                    String responseText = welcomeMessage;
                    exchange.sendResponseHeaders(200, responseText.getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(responseText.getBytes());
                    output.flush();
                } 
                else
                {
                    exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
                }
                exchange.close();
            }));

            server.createContext("/api/bye", (exchange -> {

                if ("GET".equals(exchange.getRequestMethod())) {
                    exchange.sendResponseHeaders(200, byebyeMessage.getBytes().length);
                    OutputStream output = exchange.getResponseBody();
                    output.write(byebyeMessage.getBytes());
                    output.flush();
                } 
                else
                {
                    exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
                }
                exchange.close();
            }));

            server.createContext("/api/person", new PersonHandler(store));       

            // All contexts has been created
            server.setExecutor(null); // creates a default executor
            server.start();
            System.out.println("El framework-less REST API server esta escuchando en " + server.getAddress().getAddress() + ":" + server.getAddress().getPort());

    }
 
    
}
