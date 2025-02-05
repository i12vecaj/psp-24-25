public class Numeros {
    private int numero;
    private long cuadrado;
    private long cubo;

    public Numeros(int numero) {
        this.numero = numero;
        setCuadrado(this.numero);
        setCubo(this.numero);
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

    public void setCuadrado(int numero) {
        this.cuadrado = (long) numero * (long) numero;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(int numero) {
        this.cubo = (long) numero * (long) numero * (long) numero;
    }
}
