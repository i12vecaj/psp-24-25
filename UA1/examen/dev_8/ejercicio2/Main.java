//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //Creo el objeto de la clase hilo y lo ejecuto
        Hilos hijo = new Hilos();

        hijo.start();

    }
}
/* OBSERVACIONES
    me he confundido a la hora de usar el metodo de creacion de hilos, he usado el EXTEND que hereda de una clase Thread que para el ejercicio planteado no implicaria ningun problema pero para
    un problema mas complejo deberiamos de usar el metodo IMPLEMENT que es el metodo de implementacion de HILOS que nos permite mas personalizacion.

    Tambien he querido hacer otro hilo para que en funcion del valor de la vida mostrara en vez de un valor un String el cual fuera saludable, pocha y en su prime jugando con if

 */