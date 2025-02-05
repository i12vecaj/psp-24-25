public class Miembro3 {
    public static void main(String[] args) {
        MiembroToken miembro3 = new MiembroToken(3, 10002, false, true);
        new Thread(miembro3).start();
    }
}
