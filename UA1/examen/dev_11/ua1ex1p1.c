#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// Álvaro Fernández Amaro - dev11 - 16/10/2024
// FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
// FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
// FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
// FR4: Implementa el control de errores - 2 puntos
// FR5: Documenta y estructura el código - 2 puntos


int mostrarPidProcesosHijos(int numHijo) { //Esto forma parte de la FR2, pues es una función definida que llama a los procesos.
    printf("Hola, soy el hijo nº %d de mi padre (%d), y mi PID es: %d\n", numHijo, getppid(), getpid());
}

int mostrarPidProcesoPadre() { //Esto es otra función extra que defino con el objetivo de mostrar el proceso padre cuando todos los procesos hijos terminen.
    printf("\nHola, mis hijos 1, 2, y 3 han acabado, soy el proceso padre, mi PID es: %d", getpid());
}

int error(int numHijo) {
    printf("\n--- ERROR --- Error al crear el proceso hijo %d", numHijo);
}

//Este es el proceso principal/main/padre, pues el fork lo haré en base al proceso padre.
int main() {
    
    pid_t pid_hijo1, pid_hijo2, pid_hijo3; //Declaro los procesos hijos.
    
    int numHijo; //Aquí simplemente he hecho una variable para pasarsela como parámetro a la función y poder mostrar el número del hijo que es
    
    pid_hijo1 = fork(); //Hago fork (clono) del proceso padre (main)
    
    if (pid_hijo1 == 0) {
        numHijo = 1; //Aquí y cada vez que inicializo la variable numHijo con un número es para hacer referencia al número de hijo que es.
        mostrarPidProcesosHijos(numHijo); //Llamo a la función para mostrar los PID de los procesos hijos.
        
    } else if (pid_hijo1 < 0) {
        numHijo = 1;
        error(numHijo); //Agregando esto, estoy comprobando que si el código de salida del proceso es menor a 0, (negativo), avise que hay un error.
        
    } else {
        
        wait (NULL); // Espero que acabe el primer proceso para que el siguiente pueda crear el fork.
        pid_hijo2 = fork();
        
        if (pid_hijo2 == 0) {
            
            numHijo = 2;
            mostrarPidProcesosHijos(numHijo);
            
        } else if (pid_hijo2 < 0) { //Misma forma de implementar control de errores que con el hijo 1, y con el hijo 3 haré igual (FR4).
            
            numHijo = 2;
            error(numHijo);
        
        } else {
            
            wait(NULL);
            pid_hijo3 = fork();
            
            if (pid_hijo3 == 0) {
                
                numHijo = 3;
                mostrarPidProcesosHijos(numHijo);
                
            } else if (pid_hijo3 < 0) {
                
                numHijo = 3;
                error(numHijo);
                
            } else {
                
                wait(NULL);
                mostrarPidProcesoPadre(); //Aquí llamo a la variable de mostrar el proceso padre, como finalización del programa y mostrando su PID.
                
            }
        }
    }
}

