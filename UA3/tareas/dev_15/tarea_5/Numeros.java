import java.io.Serializable;

public class Numeros implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    // Constructor
    public Numeros() {
        this.numero = 0;
        this.cuadrado = 0;
        this.cubo = 0;
    }

    // Constructor con datos
    public Numeros(int numero) {
        if (numero < 0) { // Verificamos si el número es positivo
            throw new IllegalArgumentException("El número debe ser mayor o igual a 0.");
        }
        this.numero = numero;
        this.cuadrado = (long) numero * numero;  // Calulo para sacar el cuadrado
        this.cubo = (long) numero * numero * numero; // Calulo para sacar el cubo
    }

    // Devolvemos el número almacenado
    public int getNumero() {
        return numero;
    }

    // Asignamos un nuevo valor al número y verificamos que sea positivo
    public void setNumero(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("El número debe ser mayor o igual a 0.");
        }
        this.numero = numero;
    }

    // Devolvemos el cuadrado del número
    public long getCuadrado() {
        return cuadrado;
    }

    // Asignamos manualmente el cuadrado
    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    // Devolvemos el cubo del número
    public long getCubo() {
        return cubo;
    }

    // Asignamo manualmente el cubo
    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    // Representamos en cadena del objeto
    @Override
    public String toString() {
        return "Número: " + numero + ", Cuadrado: " + cuadrado + ", Cubo: " + cubo;
    }
}