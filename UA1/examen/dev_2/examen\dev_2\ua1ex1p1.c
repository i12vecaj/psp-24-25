#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main()
{

    /*Creación de las variables que nos devolverán la información de si los procesos se han podido crear o no.*/

    pid_t pid_hijo1, pid_hijo2, pid_hijo3;

    /*Aquí se realizan varias cosas, primero se crea un proceso utilizando la función "fork()",
    al crear ese proceso se almacena el dato de si se ha podido crear en la variable pid_hijo1, pid_hijo2 y pid_hijo3 respectivamente*/

    pid_hijo1 = fork();

    /*posteriormente se comprueba el valor de la variable, en caso de ser 0 significa que se ha podido crear el proceso*/

    if (pid_hijo1 == 0)
    {
        /*Volviendo al caso de que si se cree el proceso, este if imporme por pantalla la frase "Soy el hijo X mi PID es XXXX y el PID de mi prodceso padre es XXXX\n"
        Para ello usamos la función getppid para obtener el PID del proceso padre y getpid para obtener el PID del proceso hijo*/
        printf("Soy el hijo 1 mi PID es %d y el PID de mi prodceso padre es %d\n", getppid(), getpid());
        /*Una vez impresa esa frase, el proceso es detenido unos segundos para que no se solapen por pantalla, 
        ya que de no poner la función "sleep" no llegaría a mostrarse como es debido*/
        sleep(2);
        /*Una vez impreso utilizamos el exit para detener el proceso y poder continuar con el programa*/
        exit(0);
    }

    pid_hijo2 = fork();
    if (pid_hijo2 == 0)
    {
        printf("Soy el hijo 2 mi PID es %d y el PID de mi prodceso padre es %d\n", getppid(), getpid());
        sleep(2);
        exit(0);
    }

    pid_hijo3 = fork();
    if (pid_hijo3 == 0)
    {
        printf("Soy el hijo 3 mi PID es %d y el PID de mi prodceso padre es %d\n", getppid(), getpid());
        sleep(2);
        exit(0);
    }


    /*Apara poder mostras adecuadamente el proceso padre sin que este aparezca entre medio de los hijos utilizamos otra vez la función sleep*/
    sleep(1);
    /*despues de eso mostramos por pantalla el PID del proceso padre usando la función getpid*/
    printf("Soy el proceso padre y mi PID es %d", getpid());
    /*Y cerramos el proceso usando exit como se ha realizaado previamente*/
    exit(0);
}
