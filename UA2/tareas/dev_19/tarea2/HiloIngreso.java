class HiloIngreso extends Thread {
    private final CuentaCorriente cuenta;
    private final double cantidad;

    public HiloIngreso(CuentaCorriente cuenta, double cantidad, String nombreHilo) {
        super(nombreHilo);
        this.cuenta = cuenta;
        this.cantidad = cantidad;
    }

    @Override
    public void run() {
        cuenta.añadirSaldo(cuenta.getSaldo(), cantidad );
    }
}