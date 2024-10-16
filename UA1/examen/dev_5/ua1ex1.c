/*

NOMBRE:Paco Barbero
FECHA:

PARTE PRÁCTICA

FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos

ejercicio :

#include <stdlib.h>

#include <unistd.h>

#include <stdio.h>

void main(){
    //presentamos el codigo
      printf("Hola estos son los hijos acompañado de su padre; ");
    
    //creamos los procesos hijo

  pid_t pid_hijo1,pid_hijo2,pid_hijo3;
  
  
  //hijo 1

  pid_hijo1=fork();

  if(pid_hijo1==0){



    printf("Soy el hijo mayor , Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());

    sleep(4);

    exit(0);

  }
  //hijo 2

  pid_hijo2=fork();

  if(pid_hijo2==0){

    printf("Soy el hijo de enmedio, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());

    sleep(2);

    exit(0);

  }
  
  //hijo 3

  pid_hijo3=fork();

  if(pid_hijo3==0){

    printf("Soy el hijo 3, Mi padre es= %d, Mi PID= %d\n",getppid(),getpid());

    sleep(3);

    exit(0);

  }
  //proceso padre 

  printf("  SOY EL  PADRE = %d\n",getppid());
  

  exit(0);

}










Para evitar complicaciones con máquinas virtuales, si lo prefieres puedes utilizar el compilador online: https://www.onlinegdb.com/online_c_compiler

Notas:

Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
Los nombres de las variables autodescriptivos ... siempre son bien.
Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
Los warnings del presente ... son los errores del futuro.
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

*/
