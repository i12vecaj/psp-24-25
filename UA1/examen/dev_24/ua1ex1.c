/*

PARTE PRÁCTICA 1/2

FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos

FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos

FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos

FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos
*/

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>

//Sergio Raya

int main() {
    pid_t pid;

    pid = fork();
    if (pid == -1) {
        printf("ERROR! La función Fork a fallado.");
    }

    if(pid == 0) { // Cuando fork devuelve 0 es el proceso hijo.
        printf("El proceso hijo 1 | PID: %d\n", getpid());
    } else {
        wait(NULL);
        pid = fork();
        if (pid == -1) {
            printf("ERROR! La función Fork a fallado.");
        }

        if(pid == 0) { 
            printf("El proceso hijo 2 | PID: %d\n", getpid());
        } else {
            wait(NULL);
            pid = fork();
            if (pid == -1) {
                printf("ERROR! La función Fork a fallado.");
            }

            if(pid == 0) { 
                printf("El proceso hijo 3 | PID: %d\n", getpid());
            } else {
                wait(NULL);
                printf("El proceso padre  | PID: %d\n", getppid());
            }
        }
    }
}
