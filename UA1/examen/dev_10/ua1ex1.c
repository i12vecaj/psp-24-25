NOMBRE:Ignacio de Loyola Díaz Jiménez
FECHA:16/10/24

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
/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
void main() {
  pid_t pid,Hijo1_pid,Hijo2_pid,Hijo3_pid;
  int Hijo1_pid,Hijo2_pid,Hijo3_pid;
  pid = fork();

  if (pid == -1 ) //Ha ocurrido un error 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  if (pid == 0 )  //Nos encontramos en Proceso hijo 
  {
      Hijo1_pid = getpid();
      Hijo1_pid = wait(NULL);
    printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n",  
            getpid(), getppid() );	
  }
  pid = fork();
  if (pid == -1 ) 
  {
      Hijo2_pid = getpid();
      Hijo2_pid = wait(NULL);
    printf("No se ha podido crear el proceso hijo 2...");
    exit(-1);
    
  }
  if (pid == 0 )  
  {        
    Hijo3_pid = getpid();
    Hijo3_pid = wait(NULL);
    printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n",  
            getpid(), getppid() );	
  }
   exit(0);       
}

