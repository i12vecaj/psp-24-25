public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();
        Hilo hilo1 = new Hilo(contador);
        Hilo hilo2 = new Hilo(contador);
        Hilo hilo3 = new Hilo(contador);
        Hilo hilo4 = new Hilo(contador);
        Hilo hilo5 = new Hilo(contador);
        hilo1.run();
        hilo2.run();
        hilo3.run();
        hilo4.run();
        hilo5.run();
    }
}
 class Contador {
    int contador = 0;

    public void incrementar() {
        contador+=1000;
    }

    public void getContador() {
        System.out.println(contador);
    }
}
 class Hilo implements Runnable {
    Contador contador;
     String nombre;

    public Hilo(String nombre,Contador contador) {
        this.nombre= nombre;
        this.contador = contador;
    }
    @Override
    public void run() {
        contador.incrementar();
        System.out.println("El hilo ha sumado 1000, el valor es: ");
        contador.getContador();
    }
}

//si, se obtiene el resultado esperado, ya que aunque se ejecuten en paralelo, el valor final es el mismo.
