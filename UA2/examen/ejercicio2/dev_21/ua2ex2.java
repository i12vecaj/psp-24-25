/**
Feedback JD: 12/12/2024
El código implementa correctamente la funcionalidad básica de un productor-consumidor con un buffer compartido. La sincronización está bien gestionada, y el código es claro y organizado. Sin embargo, hay oportunidades para mejorar el manejo de excepciones y el uso de Random de forma más consistente. El código es eficiente, pero algunos detalles pueden optimizarse para manejar escenarios de concurrencia más complejos.

Mejoras:

Uso de Math.random() en vez de Random: En el método generarCaracter(), usas Math.random() para generar un carácter aleatorio. Es más conveniente y consistente usar la clase Random, como en otros lugares del código, por ejemplo:

Random random = new Random();
return (char)(random.nextInt(26) + 'A');

Control de errores en run(): Aunque se lanza una excepción RuntimeException en caso de InterruptedException, sería mejor mantener el control de interrupciones y no envolverlas en una RuntimeException. Se puede utilizar Thread.currentThread().interrupt() para asegurarse de que el hilo maneje adecuadamente la interrupción.

El método consumir() podría ser más robusto: En consumir(), se elige aleatoriamente un carácter de la lista y luego se elimina. Esto es funcional, pero no se tiene en cuenta si el carácter ya ha sido consumido por otro hilo antes de que se elimine de la lista. En un escenario de alta concurrencia, podrías usar un enfoque que garantice la integridad de los datos.

El uso de notify() en el buffer: Aunque el notify() es necesario, podrías considerar el uso de notifyAll() en algunos casos si esperas que múltiples hilos puedan desbloquearse simultáneamente, especialmente cuando hay varios productores y consumidores.

**/

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class ua2ex2 {
    public static void main(String[] args) throws InterruptedException {

        Buffer buffer = new Buffer(10);

        Consumidor consumidor = new Consumidor(buffer);
        Productor productor = new Productor(buffer);
        Consumidor consumidor1 = new Consumidor(buffer);
        Productor productor1 = new Productor(buffer);


        productor.start();
        consumidor.start();
        productor1.start();
        consumidor1.start();


    }
}
class Consumidor extends Thread{
    Buffer buffer;

    Consumidor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        try {
            System.out.println("Se ha consumido el caracter "+buffer.consumir());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            espera();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void espera() throws InterruptedException {
        Random random = new Random();
        int tiempo = random.nextInt(400) + 300;
        Thread.sleep(tiempo);
    }
}

class Productor extends Thread{
    Buffer buffer;

    Productor(Buffer buffer){
        this.buffer = buffer;
    }

    @Override
    public void run(){
        char c = generarCaracter();
        System.out.println("Se ha generado el caracter "+generarCaracter());
        try {
            buffer.producir(c);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            espera();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public char generarCaracter(){
        return (char)(Math.random() * 26 + 'A');
    }
    public void espera() throws InterruptedException {
        Random random = new Random();
        int tiempo = random.nextInt(300) + 200;
        Thread.sleep(tiempo);
    }
}

class Buffer{
    private int capacidad;
    ArrayList<Character> listaCaracteres = new ArrayList<>();

    Buffer(int capacidad){
        this.capacidad = capacidad;
    }

    public synchronized void producir(char c) throws InterruptedException {
        if(capacidad > listaCaracteres.size()){
            listaCaracteres.add(c);
        }else {
            while (capacidad == listaCaracteres.size()){
                wait();
            }
        }
        notify();
    }

    public synchronized char consumir() throws InterruptedException {
        char c = 'c';
        if (listaCaracteres.isEmpty()){
            while (listaCaracteres.isEmpty()){
                wait();
            }
        }else {
            Random random = new Random();
            int posicion = random.nextInt(listaCaracteres.size());
            c = listaCaracteres.get(posicion);
            listaCaracteres.remove(posicion);
        }
        notify();
        return c;
    }
 }
