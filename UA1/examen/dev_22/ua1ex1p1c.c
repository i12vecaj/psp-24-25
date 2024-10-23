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


void hijos (int id) {
        pid_t Padre_pid = getppid();
        pid_t pid = getpid();
        hijos(id);
        printf("Hijo %d = \n Pid padre %d: Pid hijo %d: \n ", id, Padre_pid, pid);
    }

int main()
{
    int pid1, pid2, pid3;

    pid1 = fork();

    if (pid1 == 0)
    {
        hijos(1);
        exit(0);
    }

    pid2 = fork();

    if (pid2 == 0)
    {
        hijos(2);
        exit(0);
    }

    pid3 = fork();

    if (pid3 == 0)
    {
        hijos(3);
        exit(0);
    }

    return 0;
}

