public class Main {
    public static void main(String[] args) {
        //creamos los objetos que usaremos para crear los hilos y el contador y los echamos a andar
        Contador contador = new Contador();
        Hilo hilo1 = new Hilo("hilo1",contador);
        Hilo hilo2 = new Hilo("hilo2",contador);
        Hilo hilo3 = new Hilo("hilo3",contador);
        Hilo hilo4 = new Hilo("hilo4",contador);
        Hilo hilo5 = new Hilo("hilo5",contador);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
    }
}
class Contador {//creamos el objeto contador
    int contador = 0;

    public void incrementar() {//funcion para incrementar
        contador++;
    }

    public void getContador()
    {
        System.out.println(contador);
    }
}
class Hilo extends Thread {
    Contador contador;
    String nombre;

    public Hilo(String nombre,Contador contador) {
        this.nombre= nombre;
        this.contador = contador;
    }
    @Override
    public void start() {
        synchronized (contador) {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
                System.out.println("El "+nombre+" ha sumado 1000, el valor es: ");
                contador.getContador();
            }
        }
    }
}
//sincronizados y todos los hilos en orden de ejecucion
