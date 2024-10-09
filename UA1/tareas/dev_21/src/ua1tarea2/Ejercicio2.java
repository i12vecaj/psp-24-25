package ua1tarea2;

public class Ejercicio2{
    public static void main(String[] args) {
        Thread hilo = new Thread(new Hilo());
        hilo.run();
    }
}

/*
* FR1: lea una cadena de caracteres desde la entrada estándar hasta recibir un carácter de terminación, en concreto, un asterisco * - 2 puntos
FR2: una vez recibido el caracter de terminación, muestre por pantalla toda la información leída - 2 puntos
FR3: Crea después otro programa que ejecute el anterior - 2 puntos
Implementa el control de errores - 2 puntos
Documenta el código - 2 puntos*/