

public class Numeros {
    int numBase;
    long cuadrado;
    long cubo;

    public Numeros(int numBase, long cuadrado, long cubo) {
        this.numBase = numBase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }
    
    public Numeros() {
        numBase = 0;
        cuadrado = 0;
        cubo = 0;
    }

    public int getNumBase() {
        return numBase;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public long getCubo() {
        return cubo;
    }

    public void setNumBase(int numBase) {
        this.numBase = numBase;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    @Override
    public String toString() {
        return "Numeros{" + "numBase=" + numBase + ", cuadrado=" + cuadrado + ", cubo=" + cubo + '}';
    }
    
}
