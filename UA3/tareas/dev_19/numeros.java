public class numeros {
    int numerobase;
    long cuadrado;
    long cubo;

    public numeros(int numerobase, long cuadrado, long cubo) {
        this.numerobase = numerobase;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    public numeros() {
        numerobase = 0;
        cuadrado = 0;
        cubo = 0;
    }

    public int getnumerobase() {
        return numerobase;
    }

    public long getcuadrado() {
        return cuadrado;
    }

    public long getcubo() {
        return cubo;
    }

    public void setnumerobase(int numBase) {
        this.numerobase = numBase;
    }

    public void setcuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    public void setcubo(long cubo) {
        this.cubo = cubo;
    }

    @Override
    public String toString() {
        return "Numeros{" + "numerobase =" + numerobase + ", cuadrado =" + cuadrado + ", cubo =" + cubo + '}';
    }

}
