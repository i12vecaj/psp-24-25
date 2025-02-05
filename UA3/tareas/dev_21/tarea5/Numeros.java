import java.io.Serializable;

public class Numeros implements Serializable {
    private int numeroEntero;
    private long cuadrado;
    private long cubo;

    public Numeros(int numeroEntero) {
        this.numeroEntero = numeroEntero;
        this.cuadrado = 0;  
        this.cubo = 0;     
    }

    public Numeros() {
        this.numeroEntero = 0;
        this.cuadrado = 0;
        this.cubo = 0;
    }

    public int getNumeroEntero() {
        return numeroEntero;
    }

    public void setNumeroEntero(int numeroEntero) {
        this.numeroEntero = numeroEntero;
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
