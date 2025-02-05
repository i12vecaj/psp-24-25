import java.net.InetAddress;

public class Miembro3 extends MiembroToken {
    public Miembro3() throws Exception {
        super(10002, 3, false, false, InetAddress.getByName("localhost"), 10000,3);
    }

    public static void main(String[] args) throws Exception {
        Miembro3 miembro3 = new Miembro3();
        miembro3.start();
    }
}
