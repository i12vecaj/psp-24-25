package tarea_1.Mejoras;

public class ua2tarea1fr2runnable {
  public static void main(String[] args) {
    Thread[] hilosNoSinc = new Thread[3];
    Thread[] hilosSinc = new Thread[3];
    ContadorRunable contador = new ContadorRunable(0);

    // HILOS NO SINCRONIZADOS 🧵 ❌
    for (int i = 0; i < 3; i++) {
      hilosNoSinc[i] = new Thread(new HiloNoSincronizado(contador));
    }

    for (int i = 0; i < 3; i++) {
      hilosNoSinc[i].start();
    }

    for (int i = 0; i < 3; i++) {
      try {
        hilosNoSinc[i].join();
      } catch (Exception error) {
        System.out.println("No ha funcionado: " + error);
      }
    }

    // HILOS SINCRONIZADOS 🧵 ✅
    for (int i = 0; i < 3; i++) {
      hilosSinc[i] = new Thread(new HiloSincronizado(contador));
    }

    for (int i = 0; i < 3; i++) {
      hilosSinc[i].start();
    }

    for (int i = 0; i < 3; i++) {
      try {
        hilosSinc[i].join();
      } catch (Exception error) {
        System.out.println("No ha funcionado: " + error);
      }
    }
  }
}

// Clase para los hilos no sincronizados
class HiloNoSincronizado implements Runnable {
  private ContadorRunable contador;

  HiloNoSincronizado(ContadorRunable contador) {
    this.contador = contador;
  }

  @Override
  public void run() {
    for (int i = 0; i < 1000; i++) {
      contador.incrementarContador();
    }
    int valorContador = contador.getContador();
    System.out.println("El valor del contador es (no sincronizado): " + valorContador);
  }
}

// Clase para los hilos sincronizados
class HiloSincronizado implements Runnable {
  private ContadorRunable contador;

  HiloSincronizado(ContadorRunable contador) {
    this.contador = contador;
  }

  @Override
  public void run() {
    synchronized (contador) {
      for (int i = 0; i < 1000; i++) {
        contador.incrementarContador();
      }
      int valorContador = contador.getContador();
      System.out.println("El valor del contador es (sincronizado): " + valorContador);
    }
  }
}

// Clase Contador para manejar el conteo
class ContadorRunable {
  private int contador;

  ContadorRunable(int contador) {
    this.contador = contador;
  }

  public synchronized void incrementarContador() {
    contador++;
  }

  public int getContador() {
    return contador;
  }
}
