package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //Instanciamos las clases

        System.out.println("\t\t**COMENZAMOS**");
        Sensores s1 = new Sensores();
        Sensores s2 = new Sensores();
        Sensores s3 = new Sensores();

        //iniciamos distintos sensores
        Thread t = new Thread(s1);
        Thread t1 = new Thread(s2);
        Thread t2 = new Thread(s1);

        /*Primer sensor activo*/
        t.start();
        t.join();

        /*Segundo sensor activo*/
        t1.start();
        t1.join();

        /*Tercer sensor activo*/
        t2.start();
        t2.join();
    }
}