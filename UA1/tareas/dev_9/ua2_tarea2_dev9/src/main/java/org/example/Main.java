package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {


        /*Creamos las instancias de los hilos*/
        hilo_2 h2 = new hilo_2();
        hilo_1 h1 = new hilo_1();

        /*Ejecutamos y ponemos orden a los hilos*/
        h2.start();
        h2.join();
        h1.start();
        h1.join();
    }
}