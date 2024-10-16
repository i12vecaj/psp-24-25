#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main(){
    //Declaramos los 3 hijos
    pid_t pid1, pid2, pid3;

    pid1 = fork();
    //Creamos el primer hijo y escribimos por pantalla su PID y el de su padre
    if (pid1 == 0){
        printf("Soy el hijo numero 1, mi PID es %d, el PID de mi padre es %d\n", getpid(), getppid());
        exit(0);
    }

    pid2 = fork();
    //Creamos el segundo hijo y escribimos por pantalla su PID y el de su padre
    if (pid2 == 0){
        printf("Soy el hijo numero 2, mi PID es %d, el PID de mi padre es %d\n", getpid(), getppid());
        exit(0);
    }

    pid3 = fork();
     //Creamos el tercer hijo y escribimos por pantalla su PID y el de su padre, pero en este tercer hijo 
    //no se porque no me aparece a no ser que coloque texto delante suyo, por lo menos en el compilador online
    if (pid3 == 0){
        printf("Soy el hijo numero 3, mi PID es %d, el PID de mi padre es %d\n", getpid(), getppid());
        exit(0);
    }
    
    printf("El PID del programa padre es %d",getpid());
}

//Jose david esto es lo que he conseguido hacer, en alguno compiladores me salia distinto que en otros y a veces lo compilaba y salia de una manera y a veces salia de otra.