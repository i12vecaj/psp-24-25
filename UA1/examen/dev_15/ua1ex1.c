/*

NOMBRE: Alejandro Luz Morales 
FECHA: 16/10/2024

PARTE PRÁCTICA

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
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void ver_informacion_hijo(int numeroid_hijo){
    pid_t pid = getpid();
    pid_t pid_padre = getppid();
    printf("NUMERO DE HIJO %d ES EL PID: %d, QUE SU PADRE ES EL PID: %d\n", numeroid_hijo, pid, pid_padre);
}

int main(){
    pid_t pid;
    int i;
    //CREAMOS LOS 3 HIJOS
    for (i = 1; i <= 3; i++){
        pid = fork();
        if (pid == -1) {
            printf("No se ha podido crear el proceso hijo.\n");
            exit(-1);
        } else if (pid == 0) {
            ver_informacion_hijo(i);
            exit(0);
        }//EL PROCESO HIJO TERMINA
    }
    //ESPERAMOS A QUE TERMINEN TODOS 
    for (i = 1; i <= 3; i++) {
        wait(NULL);
    }
    //EL PADRE MUESTRA SU PID AL FINALIZAR EL PROGRAMA
    printf("EL PROGRAMA SE HA REALIZADO CORRECTAMENTE. ESTE ES EL PID DEL PADRE: %d\n", getpid());
    return 0;
}
