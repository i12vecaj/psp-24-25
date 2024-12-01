package com.ignaciodeloyoladiazjimenez.Mejoras;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class HiloConsumidorTest {
  private CuentaCorriente cuenta;
  private HiloConsumidor hiloConsumidor;

  @BeforeEach
  public void setUp() {
    cuenta = mock(CuentaCorriente.class);
    hiloConsumidor = new HiloConsumidor("Hilo Consumidor", 50, cuenta);
  }

  @Test
  public void testHiloConsumidor() throws InterruptedException {
    hiloConsumidor.start();
    hiloConsumidor.join();

    verify(cuenta, times(1)).restarSaldo(50);
  }
}

