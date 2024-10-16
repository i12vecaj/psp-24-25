/*

PARTE PRÁCTICA 1/2

FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos


Para evitar complicaciones con máquinas virtuales, si lo prefieres puedes utilizar el compilador online: https://www.onlinegdb.com/online_c_compiler

Notas:

Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
Los nombres de las variables autodescriptivos ... siempre son bien.
Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
Los warnings del presente ... son los errores del futuro.
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1p1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

No entregues tu solución, hasta que no se indique por parte del profesorado.

*/
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
    pid_t pid;
    pid_t pid_hijo1,pid_hijo2,pid_hijo3;

    //en caso de error
    if (pid == -1 ){
        printf("No se ha podido crear a los hijos ");
        exit(-1);
    }

    //creacion del hijo 1
    pid_hijo1=fork();
    if(pid_hijo1==0){

        printf("Hola, soy el hijo 1 mi PID es: %d y el PID de mi padre es: %d \n",getpid(),getppid());
        exit(0);
    }
    //creacion del hijo 2
    pid_hijo2=fork();
    if(pid_hijo2==0){

        printf("Hola, soy el hijo 2 mi PID es %d y el de mi padre es: %d\n",getpid(),getppid());

        exit(0);
    }

    //creacion del hijo 3
    pid_hijo3=fork();
    if(pid_hijo3 == 0);{

        printf("Hola, soy el hijo 3 mi PID es %d y el de mi padre es %d\n",getpid(),getppid());

        exit(0);
    }




    return 1;
}































