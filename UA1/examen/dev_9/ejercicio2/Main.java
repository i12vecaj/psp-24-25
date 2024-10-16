package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //Instanciamos las clases

        System.out.println("\t\t**COMENZAMOS**");
        Temperatura temperatura = new Temperatura("Temperatura");
        Humedad humedad = new Humedad("Humedad");
        EstadoPlantas plantas = new EstadoPlantas("Estado");

        //Creamos el hilo que extiende de Runnable
        Thread t = new Thread(temperatura);
        Thread h = new Thread(humedad);
        Thread p = new Thread(plantas);

        t.start();
        h.start();
        p.start();





    }
}