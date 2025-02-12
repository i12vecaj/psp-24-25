import java.io.Serializable;

public class Numeros implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    // Constructor vacío
    public Numeros() {}

    // Constructor con parámetros
    public Numeros(int numero) {
        this.numero = numero;
        this.cuadrado = (long) numero * numero;
        this.cubo = (long) numero * numero * numero;
    }

    // Getters y Setters
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

    @Override
    public String toString() {
        return "Numero: " + numero + ", Cuadrado: " + cuadrado + ", Cubo: " + cubo;
    }
}

