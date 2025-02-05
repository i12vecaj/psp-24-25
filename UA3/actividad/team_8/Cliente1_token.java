package Actividad_Cliente_Servidor_Token;

import java.net.*;

public class Cliente1_token extends MiembroToken{


    public Cliente1_token() throws Exception {
        super(10001, 1, true, false, InetAddress.getByName("localhost"), 10002, 1);
    }

    public static void main(String[] args) throws Exception {
        Cliente1_token cliente1Token = new Cliente1_token();

        cliente1Token.start();


    }


}

