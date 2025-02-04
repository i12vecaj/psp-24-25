public class Launcher {
    public static void main(String[] args) {
        new Thread(new MiembroToken(1, 10000, true, false)).start();
        new Thread(new MiembroToken(2, 10001, false, false)).start();
        new Thread(new MiembroToken(3, 10002, false, true)).start();
    }
}
