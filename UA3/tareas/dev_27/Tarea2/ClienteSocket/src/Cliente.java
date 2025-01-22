import java.io.DataOutputStream;
import java.io.IOException;

public class Cliente extends Conexion{
    public Cliente() throws IOException{super("cliente");}

    public void iniciar_cliente(){
        try{
            salidaServidor = new DataOutputStream(cliente.getOutputStream());
            for (int i=1; i==1; i++){
                salidaServidor.writeUTF("El puerto del cliente es "+cliente.getLocalPort());
            }
            cliente.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
