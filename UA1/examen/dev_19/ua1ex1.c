//FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
//FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
//FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
//FR4: Implementa el control de errores - 2 puntos
//FR5: Documenta y estructura el código - 2 puntos

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
	 pid_t pid, 
	pid_hijo1, pid_hijo2,pid_hijo3;  //Creamos Padre e Hijos
	int status1, status2, status3;

	pid = fork();   //inicializamos fork con el padre
	 
    //Si el fork devuelve 0, este sería el hijo
	if (pid==-1){
     printf("No se ha podido crear el proceso hijo...");//evitamos asi los errores en la creacion
    exit(-1);   
  }
    if ( (pid_hijo1=fork()) == 0 )
    { //primer hijo con su id e id del padre
        printf("Soy el primer hijo (%d, hijo de %d)\n",  getpid(), getppid());
    }
    else
    { 
        if ( (pid_hijo2=fork()) == 0 )
        { //segundo hijo con su id e id del padre
            printf("Soy el segundo hijo (%d, hijo de %d)\n",  getpid(), getppid());
        }
        else
        { if((pid_hijo3=fork())==0){
          //tercer hijo con su id e id del padre
            printf("Soy el tercer hijo(%d, hijo de %d)\n",  getpid(), getppid());
        }else{
          //padre
            // Esperamos al primer hijo 
            waitpid(pid_hijo1, &status1, 0);
            // Esperamos al segundo hijo 
            waitpid(pid_hijo2, &status2, 0);
            // Esperamos al tercer hijo
            waitpid(pid_hijo3, &status3,0);
            printf("Soy el padre (%d, hijo de %d)\n", getpid(), getppid());
        }

        }
    }


	return 0;
}