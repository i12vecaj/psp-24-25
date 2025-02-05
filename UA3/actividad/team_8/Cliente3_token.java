package Actividad_Cliente_Servidor_Token;

import java.net.InetAddress;

public class Cliente3_token extends MiembroToken {
    public Cliente3_token() throws Exception {
        super(10003, 1, false, false, InetAddress.getByName("localhost"), 10001, 3);
    }

    public static void main(String[] args) throws Exception {
        Cliente3_token miembro3 = new Cliente3_token();
        miembro3.start();
    }
}