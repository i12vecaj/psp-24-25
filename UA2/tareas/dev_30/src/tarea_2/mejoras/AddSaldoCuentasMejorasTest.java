package tarea_2.mejoras;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddSaldoCuentaTestMejorasTest {

    private CuentaCorriente cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new CuentaCorriente(2000);
    }

    @Test
    public void testAddSaldo() throws InterruptedException {
        // Crear los hilos que van a añadir saldo
        Thread hilo1 = new Thread(new AddSaldoCuenta("pepe Moriles", 500));
        Thread hilo2 = new Thread(new AddSaldoCuenta("Jose Pablo", 600));
        Thread hilo3 = new Thread(new AddSaldoCuenta("Manolo Torres", 700));

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Verificar que el saldo final sea el esperado
        assertEquals(3800, CuentaCorriente.getSaldo());
    }

    @Test
    public void testAddSaldoWithMultipleThreads() throws InterruptedException {
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

        // Verificar que el saldo final sea 3800
        assertEquals(3800, CuentaCorriente.getSaldo());
    }
}
