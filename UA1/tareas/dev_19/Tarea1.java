
/*
    CFGS Desarrollo Aplicaciones Multiplataforma
    Programación Servicios y Procesos
    CES Lope de Vega - Córdoba (Spain)
    Curso 2022/2023
*/

/*
    Tarea 1 - Programación de Procesos en C
    Criterios a), f), g), h)
    Crea un programa en C que cree un proceso (existirán en realidad dos procesos, uno padre y el otro hijo). 
    El programa principal pedirá al usuario una variable y el proceso padre restará 5 a dicha variable, mientras que el proceso hijo le sumará 4. 
    Muestra todos los valores por pantalla.
*/
//import java.*;
 
//package ua1;

// https://medium.com/@pelensky/java-tdd-with-junit-without-using-an-ide-cd24d38adff
public class Tarea1 
{
    //public void main(String[] args) {
    //    System.out.println("Hello, World!"); 
    //}

    public String hello() {
        return("Hello, World!"); 
    }

    public int add(int op1, int op2)
    {int suma = op1+op2;
        return suma;
    }
    public void goodBye() {
        System.out.println("Good bye, World!"); 
    }
}
