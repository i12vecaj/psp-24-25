public class IngresoPorExtends extends Thread {
    private CuentaCorriente cuenta;

    public IngresoPorExtends(CuentaCorriente cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hilo activo: " + Thread.currentThread().getName());
            System.out.println("Prioridad del hilo: " + Thread.currentThread().getPriority());

            cuenta.ingresar(10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
