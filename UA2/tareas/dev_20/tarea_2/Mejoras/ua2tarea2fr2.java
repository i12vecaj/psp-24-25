package tarea_2.mejoras;

public class ua2tarea2fr2 {
  public static void main(String[] args) throws InterruptedException {
    // Instancio la cuenta corriente y los hilos
    CuentaCorriente cuentaCorriente = new CuentaCorriente();
    Thread hilo = new Thread(new Hilo(cuentaCorriente, 100), "Hilo con menor prioridad");
    Thread hilo2 = new Thread(new Hilo(cuentaCorriente, 50), "Hilo con mayor prioridad");

    hilo.setPriority(Thread.MIN_PRIORITY); // Mínima prioridad
    hilo2.setPriority(Thread.MAX_PRIORITY); // Máxima prioridad
    // Inicio los hilos
    hilo.start();
    hilo2.start();
    // Espero a que terminen los hilos
    hilo.join();
    hilo2.join();
  }
}
// Hilo que agrega valores aleatorios a la cuenta corriente
class Hilo extends Thread {
  CuentaCorriente cuentaCorriente;
  int addicion;

  // Constructor en el que instancio la cuenta corriente
  Hilo(CuentaCorriente cuenta, int addicion) {
    this.cuentaCorriente = cuenta;
    this.addicion = addicion;
  }

  @Override
  public void run() {
    // Utilizo el método sincronizado de cuenta corriente para agregar el saldo
    cuentaCorriente.agregarSaldo(addicion);
  }
}