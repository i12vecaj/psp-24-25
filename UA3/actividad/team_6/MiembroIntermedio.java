public class MiembroIntermedio {
    public static void main(String[] args) {
        NodoToken nodo2 = new NodoToken(2, 10001, false, false);
        new Thread(nodo2).start();
    }
}