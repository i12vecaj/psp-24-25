import java.net.InetAddress;

public class Miembro1 extends MiembroToken {
    public Miembro1() throws Exception {
        super(10000, 1, true, false, InetAddress.getByName("localhost"), 10001, 1);
    }

    public static void main(String[] args) throws Exception {
        Miembro1 miembro1 = new Miembro1();
        miembro1.start();
    }
}
