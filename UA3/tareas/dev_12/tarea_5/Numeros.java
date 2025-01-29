package tarea5;

import java.io.Serializable;

public class Numeros implements Serializable {
    private int numeroEntero;
    private long cuadrado;
    private long cubo;

    // Constructor con parámetros
    public Numeros(int numeroEntero) {
        this.numeroEntero = numeroEntero;
        this.cuadrado = 0;
        this.cubo = 0;
    }

    // Constructor vacío
    public Numeros() {
        this.numeroEntero = 0;
        this.cuadrado = 0;
        this.cubo = 0;
    }

    // Getters y setters
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

    @Override
    public String toString() {
        return "Numero: " + numeroEntero + ", Cuadrado: " + cuadrado + ", Cubo: " + cubo;
    }
}
