package tarea_2.mejoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ua2tarea2Tests {

  private CuentaCorriente cuentaCorriente;

  @BeforeEach
  public void setUp() {
    cuentaCorriente = new CuentaCorriente();
  }

  @Test
  public void testGetSaldo() throws InterruptedException {
    // Test que verifica el saldo inicial
    assertEquals(100, cuentaCorriente.getSaldo(), "El saldo inicial debe ser 100");
  }

  @Test
  public void testSetSaldo() throws InterruptedException {
    // Test que verifica el método setSaldo
    cuentaCorriente.setSaldo(200);
    assertEquals(200, cuentaCorriente.getSaldo(), "El saldo debe actualizarse a 200");
  }

  @Test
  public void testAgregarSaldo() throws InterruptedException {
    // Test que verifica el método agregarSaldo
    cuentaCorriente.agregarSaldo(50);
    assertEquals(150, cuentaCorriente.getSaldo(), "El saldo debe incrementarse correctamente por 50");

    cuentaCorriente.agregarSaldo(100);
    assertEquals(250, cuentaCorriente.getSaldo(), "El saldo debe incrementarse correctamente por 100");
  }

  @Test
  public void testHilosConcurrentes() throws InterruptedException {
    // Test para verificar que los hilos no causen inconsistencias
    Thread hilo1 = new Thread(new Hilo(cuentaCorriente, 50));
    Thread hilo2 = new Thread(new Hilo(cuentaCorriente, 100));

    hilo1.start();
    hilo2.start();

    hilo1.join();
    hilo2.join();

    // Verifica que el saldo final esté dentro del rango esperado
    int saldoFinal = cuentaCorriente.getSaldo();
    assertTrue(saldoFinal >= 250 && saldoFinal <= 300,
            "El saldo final debe estar entre 250 y 300 debido a las sumas de los hilos");
  }

  @Test
  public void testHilosConPrioridad() throws InterruptedException {
    // Test con hilos de diferente prioridad
    Thread hiloBajo = new Thread(new Hilo(cuentaCorriente, 200));
    Thread hiloAlto = new Thread(new Hilo(cuentaCorriente, 50));

    hiloBajo.setPriority(Thread.MIN_PRIORITY);
    hiloAlto.setPriority(Thread.MAX_PRIORITY);

    hiloBajo.start();
    hiloAlto.start();

    hiloBajo.join();
    hiloAlto.join();

    // Verifica que no haya inconsistencias en el saldo después de la ejecución
    int saldoFinal = cuentaCorriente.getSaldo();
    assertTrue(saldoFinal >= 350 && saldoFinal <= 400,
            "El saldo final debe estar entre 350 y 400 debido a las sumas de los hilos");
  }
}