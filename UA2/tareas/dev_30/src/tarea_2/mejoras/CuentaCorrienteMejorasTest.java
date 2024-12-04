package tarea_2.mejoras;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CuentaCorrienteMejorasTest {

    @Test
    public void testSaldoInicial() throws InterruptedException {
        // Verificar saldo inicial
        CuentaCorriente cuenta = new CuentaCorriente(2000);
        assertEquals(2000, CuentaCorriente.getSaldo());
    }

    @Test
    public void testAgregarSaldoSincronizado() throws InterruptedException {
        CuentaCorriente cuenta = new CuentaCorriente(2000);

        // Hilos que incrementan el saldo
        Thread hilo1 = new Thread(new AddSaldoCuenta("pepe Moriles", 500));
        Thread hilo2 = new Thread(new AddSaldoCuenta("Jose Pablo", 600));
        Thread hilo3 = new Thread(new AddSaldoCuenta("Manolo Torres", 700));

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Verificar que el saldo final es el esperado
        assertEquals(3800, CuentaCorriente.getSaldo());
    }
}
