public class HiloCuentaCorriente extends Thread {
    private CuentaCorriente cuenta;
    private double cantidad;
    private String nombre;

    public HiloCuentaCorriente(CuentaCorriente cuenta, double cantidad, String nombre) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.setName(nombre);
    }

    @Override
    public void run() {
        cuenta.añadirSaldo(cantidad, nombre);
    }
}
