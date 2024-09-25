
    import java.io.*;
    import java.net.*;
    import java.util.Scanner;
    
    public class tarea3cliente {
    
        
        public static void main(String[] args) throws IOException{
            
            
             Scanner escanner = new Scanner(System.in);
             Socket cliente = new Socket("localhost", 2000);
            
            PrintWriter fuera = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
    
            
            System.out.println("Introduce texto");
            String mensaje_Cliente = escanner.nextLine();
            
            
        
            fuera.println(mensaje_Cliente); 
            String respuestaServidor = in.readLine();
            
            System.out.println(respuestaServidor);
            cliente.close(); 
        }
        
    }   
    
