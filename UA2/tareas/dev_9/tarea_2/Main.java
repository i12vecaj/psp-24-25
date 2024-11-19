package org.example;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*
        * Creamos los diferentes campos que va a utilizar el usuario así como
        * los compradores, nombres y cantidades.
        * */
        ArrayList<Comprador> compradores = new ArrayList<Comprador>();
        String [] nombres = {"Juan","Miguel","Manolo","Francisco","José"};
        Float [] cantidades = {100.f,200.f,150.f,200.f,1000.f};

        /*
        * Creamos una instancia de cuenta la cual he decidido que sea utilizada por
        * todos los hilos siguientes y de este modo se va a compartir por todos los
        * hilos o compradores.
        * */
        CuentaCorriente cuenta = new CuentaCorriente();

        /*
        * Vamos a crear 5 compradores y los inicializamos en su constructor con el nombre,
        * cuenta la cual va a ser compartida y la cantidad.
        * Además voy a establecer diferentes prioridades las cuales van a ir de forma escalar,
        * es decir, el primer comprador que se cree va a tener la prioridad de uno, dos, 3 ...
        * hasta 5.
        * */
        int contador = 0;
        for (int i = 0; i < 5; i++) {
            compradores.add(new Comprador(nombres[i],cuenta,cantidades[i]));
            compradores.get(i).setPriority(++contador);
            compradores.get(i).start();
        }

        while (true) {
            boolean todosTerminados = true;

            for (Comprador c : compradores) {
                if (c.isAlive()) {
                    todosTerminados = false; // Si alguno está vivo, marcamos como no terminado
                    break;
                }
            }

            if (todosTerminados) {
                System.out.println("\n\t***\nTODOS LOS COMPRADORES HAN TERMINADO.");
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
        * En el fr4 si no utilizamos el syncronized es probable que haya problemas en cuanto a la
        * condicion de carrera ya que en el caso de que hubiese dos hilos accediendo a un mismo recurso
        * ese recurso devolvería datos incorrectos
        * */


        /*
        * Aqui ha terminado el programa
        * */
    }
}