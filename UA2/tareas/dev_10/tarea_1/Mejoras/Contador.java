package tarea_1.Mejoras;

public class Contador {
  private int valor = 0;
  private final boolean sincronizado;

  // Constructor que acepta un parámetro para definir si usar o no sincronización
  public Contador() {
    this.sincronizado = true;
  }

  // Constructor sin sincronización
  public Contador(boolean sincronizado) {
    this.sincronizado = sincronizado;
  }

  // Método para incrementar el contador
  public synchronized void incrementar() {
    valor++;
  }

  // Método para obtener el valor del contador
  public int obtenerValor() {
    return valor;
  }
}
