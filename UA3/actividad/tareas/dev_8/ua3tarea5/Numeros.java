import java.io.Serializable;

public class Numeros implements Serializable {
    int numero1;
    long numero2, numero3;

    public Numeros() {
    }

    public Numeros(int numero1, long numero2, long numero3) {
        this.numero1 = numero1;
        this.numero2 = numero2;
        this.numero3 = numero3;
    }

    public int getNumero1() {
        return numero1;
    }

    public long getNumero2() {
        return numero2;
    }

    public long getNumero3() {
        return numero3;
    }

    public void setNumero1(int numero1) {
        this.numero1 = numero1;
    }

    public void setNumero2(long numero2) {
        this.numero2 = numero2;
    }

    public void setNumero3(long numero3) {
        this.numero3 = numero3;
    }
}