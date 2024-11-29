public class ua2tarea2 {

    public static void main(String[] args) {
        CuentaCorriente cuenta = new CuentaCorriente(1000.00f);

        Thread hilo1 = new Thread(new AñadirSaldo("Jose Martinez", 1000f));
        Thread hilo2 = new Thread(new AñadirSaldo("Jose Jose", 4000f));
        Thread hilo3 = new Thread(new AñadirSaldo("Jose Juan", 1500f));

        hilo1.start();
        hilo2.start();
        hilo3.start();


    }
}
