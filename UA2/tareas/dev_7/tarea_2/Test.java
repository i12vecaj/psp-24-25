import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test {

    @Test
    public void testSaldoInicial() {
        CuentaCorriente cuenta = new CuentaCorriente();
        assertEquals(1000, cuenta.getSaldo(), "El saldo inicial debería ser 1000.");
    }

    @Test
    public void testDepositar() throws InterruptedException {
        CuentaCorriente cuenta = new CuentaCorriente();

        Thread hilo1 = new Thread(() -> cuenta.depositar(500, "Hilo 1"));
        Thread hilo2 = new Thread(() -> cuenta.depositar(300, "Hilo 2"));
        Thread hilo3 = new Thread(() -> cuenta.depositar(200, "Hilo 3"));

        hilo1.start();
        hilo2.start();
        hilo3.start();

        // Esperamos a que los hilos terminen.
        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Validamos el saldo final.
        assertEquals(2000, cuenta.getSaldo(), "El saldo final debería ser 2000 después de las transacciones.");
    }

    @Test
    public void testConcurrente() throws InterruptedException {
        CuentaCorriente cuenta = new CuentaCorriente();

        // Creamos varios hilos para realizar transacciones concurrentes.
        Thread[] hilos = new Thread[10];
        for (int i = 0; i < hilos.length; i++) {
            int cantidad = (i + 1) * 100; // Cada hilo depositará una cantidad distinta.
            hilos[i] = new Thread(() -> cuenta.depositar(cantidad, "Hilo " + cantidad));
        }

        // Iniciamos todos los hilos.
        for (Thread hilo : hilos) {
            hilo.start();
        }

        // Esperamos a que todos los hilos terminen.
        for (Thread hilo : hilos) {
            hilo.join();
        }

        // Validamos el saldo final.
        int saldoEsperado = 1000; // Saldo inicial
        for (int i = 0; i < hilos.length; i++) {
            saldoEsperado += (i + 1) * 100;
        }
        assertEquals(saldoEsperado, cuenta.getSaldo(), "El saldo final debería reflejar correctamente todas las transacciones.");
    }
}
