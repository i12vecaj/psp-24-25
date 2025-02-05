import java.net.InetAddress;

public class Miembro3 extends MiembroToken {
    public Miembro3() throws Exception {
        //super(10002, 2, false, false, InetAddress.getByName("10.2.1.232"), 10000, 3);
        super(10002, 2, false, false, InetAddress.getByName("localhost"), 10000, 3);

    }

    public static void main(String[] args) throws Exception {
        Miembro3 miembro2 = new Miembro3();
        miembro2.start();
    }
}
