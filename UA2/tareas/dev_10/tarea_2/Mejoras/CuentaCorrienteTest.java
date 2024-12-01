package com.ignaciodeloyoladiazjimenez.Mejoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaCorrienteTest {
  private CuentaCorriente cuenta;

  @BeforeEach
  public void setUp() {
    cuenta = new CuentaCorriente();
  }

  @Test
  public void testAgregarSaldo() throws InterruptedException {
    double saldoInicial = cuenta.getSaldo();
    cuenta.agregarSaldo(50);
    assertEquals(saldoInicial + 50, cuenta.getSaldo(), 0.01);
  }

  @Test
  public void testRestarSaldo() throws InterruptedException {
    double saldoInicial = cuenta.getSaldo();
    cuenta.restarSaldo(20);
    assertEquals(saldoInicial - 20, cuenta.getSaldo(), 0.01);
  }

  @Test
  public void testSetSaldo() throws InterruptedException {
    cuenta.setSaldo(200);
    assertEquals(200, cuenta.getSaldo(), 0.01);
  }

  @Test
  public void testGetSaldo() throws InterruptedException {
    assertEquals(100, cuenta.getSaldo(), 0.01);
  }
}

