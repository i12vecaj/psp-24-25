import java.io.*;
import java.net.*;
import java.util.*;

public class tarea4cliente {
    private String mensaje;
       
    public tarea4cliente()
    {
    
    mensaje = "";
    }

    public String getMensaje()
    {
    return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public static void main(String[] args)throws Exception
    {
      
        
        String host = "localhost";

        int port = 6000;
        Socket cliente = new Socket(host,port);
        

        DataOutputStream flujo_salida = new DataOutputStream(cliente.getOutputStream());
        tarea4cliente c = new tarea4cliente();
        c.setMensaje(mensajear());
        flujo_salida.writeUTF(c.getMensaje());
        flujo_salida.close();

        cliente.close();
    }

     public static String mensajear()
     {
        Scanner scan = new Scanner(System.in);
        String mensaje="";
        System.out.println("Introduce un mensaje: ");
        while (!mensaje.endsWith("*")) 
        {
			mensaje = mensaje + scan.nextLine();

		}

        if (mensaje.endsWith("*")) 
        {
			mensaje = mensaje.substring(0,(mensaje.length() -1));
		}
        return mensaje;
    }
    }

