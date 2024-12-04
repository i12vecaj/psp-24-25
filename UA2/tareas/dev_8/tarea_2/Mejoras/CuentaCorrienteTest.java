import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CuentaCorrienteTest {

    @Test
    public void testSaldoFinal() throws InterruptedException {
        CuentaCorriente cuenta = new CuentaCorriente(1000.0);

        IngresoPorExtends hilo1 = new IngresoPorExtends(cuenta);
        IngresoPorExtends hilo2 = new IngresoPorExtends(cuenta);
        IngresoPorExtends hilo3 = new IngresoPorExtends(cuenta);
        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        // Verificar que el saldo final es el esperado
        double saldoEsperado = 1000.0 + (Stream.of(hilo1, hilo2, hilo3).mapToDouble(ingresoPorExtends -> {
            return ingresoPorExtends.getIngreso();
        }).sum());
        assertEquals(saldoEsperado, cuenta.getSaldo());
    }
}