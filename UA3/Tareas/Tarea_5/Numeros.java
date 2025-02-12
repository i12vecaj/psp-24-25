import java.io.Serial;
import java.io.Serializable;

public class Numeros implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L; // Identificador único para la serialización

  private int numero;
  private long cuadrado;
  private long cubo;

  public Numeros() {
  }

  public Numeros(int numero) {
    this.numero = numero;
  }

  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
    this.numero = numero;
  }

  public long getCuadrado() {
    return cuadrado;
  }

  public void setCuadrado(long cuadrado) {
    this.cuadrado = cuadrado;
  }

  public long getCubo() {
    return cubo;
  }

  public void setCubo(long cubo) {
    this.cubo = cubo;
  }
}
