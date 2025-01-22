import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Servidor extends Conexion
{
    public Servidor() throws IOException{super("servidor");}
    public void iniciar_servidor(){
        try{
            System.out.println("Esperando...");
            cliente=servidor.accept();
            System.out.println("Cliente conectado");
            salidaCliente = new DataOutputStream(cliente.getOutputStream());
            salidaCliente.writeUTF("Primer mensaje al cliente");
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

            while((mensajeservidor = entrada.readLine()) != null) //Mientras haya mensajes desde el cliente
            {
                //Se muestra por pantalla el mensaje recibido
                System.out.println(mensajeservidor);
            }

            System.out.println("Fin de la conexión");

            servidor.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
