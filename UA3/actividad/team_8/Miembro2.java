import java.net.InetAddress;

public class Miembro2 extends MiembroToken {
    public Miembro2() throws Exception {
        super(10001, 2, false, false, InetAddress.getByName("localhost"), 10002, 2);
    }

    public static void main(String[] args) throws Exception {
        Miembro2 miembro2 = new Miembro2();
        miembro2.start();
    }
}
