/*

NOMBRE: Juan Deogracias Moya
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


/*
* Creamos una funcion que nos permite mostrar por pantalla el numero de hijo que es
* el pid de el proceso y el pid de el proceso padre
*/
void imprimir(int numero,int pid_hijo, int pid_padre) {
    printf("Soy el hijo %d , PID_Hijo : %d, PID_Padre :%d\n",numero, getpid(),getppid());
}


/*          IMPORTANTE
* fork == -1  Nos devolvería un error
* fork == 0  Estamos en el proceso hijo
* fork == 1  Estamos en el proceso padre
*/
int main()
{
    /*
    * Definimos cada una de los procesos que vamos ha tener en este caso como
    * tenemos tres hijos tendremos tres variables de los distintos 3 hijos.
    */
    pid_t pid_1,pid_2,pid_3;
    
    printf("\t\t**BIENVENIDO**\n\n");
    /*
    * Mediante fork() estamos haciendo una copia exacta de el proceso padre
    * y mediante las condiciones if-else podemos decirle a cada una de las copias o
    * procesos que es lo que tiene que hacer en cada momento.
    */
    pid_1 = fork();
    
    /*
    * Aplicamos el control de errores
    */
    if(pid_1 < 0 ) {
        printf("Error");
        return -1;
    }
    
    if(pid_1 == 0) {
        
        imprimir(1,getpid(),getppid());
        
    }else {
        /*
        * obligamos al proceso padre a que espere la ejecucion de los hijos
        */
        wait(NULL);
        
        pid_2 = fork();
        
        /*
        * Implementamos control de errores de modo que si devulve un numero que no
        * es ni cero ni uno estaríamos en un Error
        */
        if(pid_2 < 0 ) {
            printf("Error");
            return -1;
        }
        
        if(pid_2 == 0) {
            /*
            * Creamos un proceso hijo denominado pid_2 dentro de el proceso main
            */
            imprimir(2,getpid(),getppid());
            
        }else {
            
            /*
            * Creamos un proceso hijo denominado pid_3 dentro de el proceso main
            */
            pid_3 = fork();
            
            if(pid_3 < 0 ) {
                printf("Error");
                return -1;
            }
            
            if(pid_3 == 0) {
                /*
                * Llamamos a la funcion para imprimir por pantalla el pid de hijo y padre
                */
                imprimir(3,getpid(),getppid());
                
            } else {
                /*
                * implementamos wait(NULL) de modo que el proceso padre espere 
                * la finalización de la ejecución de los procesos hijos
                */

                wait(NULL);
                
                /*
                * Mostramos en pantalla el pid de el proceso padre de todos el cual es el main
                */
                printf("Soy el proceso padre de todos (main) con PID %d\n", getpid());
                
                printf("\n\t**EL PROGRAMA A FINALIZADO**\n\n");
            }
        }
    }
}
