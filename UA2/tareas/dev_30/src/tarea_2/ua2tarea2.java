package tarea_2;

public class ua2tarea2 {
    public static void main(String[] args) throws InterruptedException {

        CuentaCorriente cuenta = new CuentaCorriente(2000);

        Thread Hilo1 = new Thread(new AddSaldoCuenta("pepe Moriles",500));

        Thread Hilo2 = new Thread(new AddSaldoCuenta("Jose Pablo",600));

        Thread Hilo3 = new Thread(new AddSaldoCuenta("Manolo Torres",700));


        Hilo1.start();
        Hilo2.start();
        Hilo3.start();





    }
}
