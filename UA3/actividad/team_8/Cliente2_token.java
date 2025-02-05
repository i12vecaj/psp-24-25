package Actividad_Cliente_Servidor_Token;

import java.net.InetAddress;

public class Cliente2_token extends MiembroToken {
    public Cliente2_token() throws Exception {
        //super(10001, 1, false, false, InetAddress.getByName("10.2.1.223"), 10002, 2);
        super(10001, 1, false, false, InetAddress.getByName("localhost"), 10003, 2);
    }

    public static void main(String[] args) throws Exception {
        Cliente2_token miembro2 = new Cliente2_token();
        miembro2.start();
    }

}