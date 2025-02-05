public class Miembro1 {
    public static void main(String[] args) {
        MiembroToken miembro1 = new MiembroToken(1, 10000, true, false);
        new Thread(miembro1).start();
    }
}

