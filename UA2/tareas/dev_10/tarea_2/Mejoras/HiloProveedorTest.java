package com.ignaciodeloyoladiazjimenez.Mejoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class HiloProveedorTest {
  private CuentaCorriente cuenta;
  private HiloProveedor hiloProveedor;

  @BeforeEach
  public void setUp() {
    cuenta = mock(CuentaCorriente.class);
    hiloProveedor = new HiloProveedor("Hilo Proveedor", 40, cuenta);
  }

  @Test
  public void testHiloProveedor() throws InterruptedException {
    hiloProveedor.start();
    hiloProveedor.join();

    verify(cuenta, times(1)).agregarSaldo(40);
  }
}

