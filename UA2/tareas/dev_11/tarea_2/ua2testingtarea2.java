import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CuentaCorrienteTest {

    private ua2tarea2.CuentaCorriente cuenta;

    //Este metodo lo que hace es instanciar un objeto de la clase CuentaCorriente
    @BeforeEach
    void setUp() {
        cuenta = new ua2tarea2.CuentaCorriente(1000);
    }

    @Test
    void testSaldoInicial() { // Este test comprueba que el saldo inicial es 1000, puesto que es el saldo inicial que indico al instanciar el objeto arriba
        assertEquals(1000, cuenta.getSaldo(), "El saldo inicial debe ser 1000€.");
    }

    @Test
    void testAddSaldo() { // Este test comprueba que el saldo se añade correctamente, es decir que si se añaden 300 euros, el saldo esperado sea 1300
        cuenta.addSaldo(300, "Álvaro Tester ");
        int saldoEsperado = 1300;
        assertEquals(saldoEsperado, cuenta.getSaldo(), "El saldo debería incrementarse en 300€.");
    }

    //Este test comprueba el funcionamiento por así decirlo del main, puesto que realiza la misma logica y comprueba que el saldo final esperado concuerde con lo que sucede en el programa
    @Test
    void testAddSaldoFinal() throws InterruptedException {
        ua2tarea2.HiloIngreso hilo1 = new ua2tarea2.HiloIngreso(cuenta, 300, "Álvaro");
        ua2tarea2.HiloIngreso hilo2 = new ua2tarea2.HiloIngreso(cuenta, 150, "Juan");
        ua2tarea2.HiloIngreso hilo3 = new ua2tarea2.HiloIngreso(cuenta, 200, "Fran");

        hilo1.start();
        hilo2.start();
        hilo3.start();

        hilo1.join();
        hilo2.join();
        hilo3.join();

        int saldoEsperado = 1000 + 300 + 150 + 200;
        assertEquals(saldoEsperado, cuenta.getSaldo(), "El saldo final debe reflejar los ingresos de todos los usuarios en un saldo final.");
    }
}
