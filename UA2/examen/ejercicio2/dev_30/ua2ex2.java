/**
Feedback JD: 12/12/2024

Manuel como sabes tu código no representa del todo el productor - consumidor, ya que una de las partes no está bien implementada. Aunque doy como válida tu solución.

Además, he visto algunos aspectos mejorables:

Se utiliza join() en el main, lo que provoca que el productor finalice completamente antes de que el consumidor inicie, eliminando el comportamiento concurrente esperado.
El consumidor imprime el contenido completo del buffer (getNumeros()), pero no elimina los elementos consumidos, lo que contradice el propósito del consumo.
Los mensajes "El consumidor ha terminado" y "El productor ha terminado" están intercambiados en los métodos run() de las respectivas clases.
Aunque se utiliza wait() y notifyAll(), no se verifican adecuadamente las condiciones del buffer al consumir o producir en algunos casos, lo que puede generar inconsistencias en escenarios más complejos.

**/

import java.util.ArrayList;

public class ua2ex2 {


    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        Thread productor = new Thread(new Productor(buffer));
        Thread consumidor = new Thread(new Consumidor(buffer));



            productor.start();
            productor.join();

            consumidor.start();
            consumidor.join();


    }
}

class Buffer{
    private ArrayList<Character> numeros = new ArrayList<Character>();

    public Buffer() {

    }

    public ArrayList<Character> getNumeros() {
        return numeros;
    }


    public synchronized void producir(char c) throws InterruptedException {
        if (numeros.size()==10){
            wait();
        }

        numeros.add(c);
        notifyAll();
    }

    public synchronized void consumir() throws InterruptedException {
        while (numeros.isEmpty()){
            wait();
        }

        System.out.println("El consumidor ha consumido el producto: "+getNumeros());
        notifyAll();
    }

}
class Productor extends Thread{

    public Buffer buffer;

    public Productor(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {


        try {
            for (int i=0;i<10;i++){
                char numeroAleatorio = (char) (Math.random() * 26 + 'A');
                buffer.producir(numeroAleatorio);
                System.out.println("El productor ha producido un elemento: "+ buffer.getNumeros());
            }


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("El consumidor ha terminado");
    }
}

class Consumidor extends Thread{
    public Buffer buffer;

    public Consumidor(Buffer buffer){
        this.buffer=buffer;

    }

    @Override
    public void run() {

        try {
            for (int i=0;i<10;i++){
                buffer.consumir();
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El productor ha terminado");
    }
}

