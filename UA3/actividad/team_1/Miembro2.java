public class Miembro2 {
    public static void main(String[] args) {
        MiembroToken miembro2 = new MiembroToken(2, 10001, false, false);
        new Thread(miembro2).start();
    }
}
