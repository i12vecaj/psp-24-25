public class HiloIngreso extends Thread {
    private CuentaCorriente cuenta;
    private double cantidad;
    private String nombre;

    public HiloIngreso(CuentaCorriente cuenta, double cantidad, String nombre) {
        this.cuenta = cuenta;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        cuenta.agregarSaldo(cantidad, nombre);
    }
}
