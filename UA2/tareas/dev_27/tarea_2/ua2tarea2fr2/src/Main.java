package UA2.tareas.dev_0.tarea_2.ua2tarea2fr2.src;

public class Main extends Thread{
    @Override
    public void run() {
        CuentaCorriente cuenta = new CuentaCorriente();
        cuenta.setSaldoCuenta(0);
        cuenta.anadirSaldo();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }
}