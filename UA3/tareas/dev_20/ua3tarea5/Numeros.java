package dev_20.ua3tarea5;

import java.io.Serializable;

public class Numeros implements Serializable {
    int entero;
    long cuadrado;
    long cubo;

    public Numeros(int entero, long cuadrado, long cubo) {
        setEntero(entero);
        setCuadrado(cuadrado);
        setCubo(cubo);
    }
    public Numeros(int entero){
        setEntero(entero);
    }
    public Numeros(){}

    public int getEntero() {
        return entero;
    }
    public void setEntero(int entero) {
        this.entero = entero;
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
        return "Numeros{" +
                "entero=" + entero +
                ", cuadrado=" + cuadrado +
                ", cubo=" + cubo +
                '}';
    }
}
