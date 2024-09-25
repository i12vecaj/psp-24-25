/*
NOMBRE:Alvaro Miguel Losada Romero
FECHA:26/10/2022
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

#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
	 pid_t pid, 
	pid_hijo1, pid_hijo2;  //Creamos Padre e Hijos
	
	pid = fork();   //Creamos fork con el Padre
	

    
    //Si el fork devuelve 0, este sería el hijo
	
	if (pid == 0) { 

		sleep(3);  //Despues de llamar al hijo, usamos sleep hasta aparecer el siguiente

	printf("Hijo[1] pid = %d y mi padre = %d\n",  //Mostramos hijo y su padre
			getpid(), getppid());
	}

	else {
		pid_hijo1 = fork();
		if (pid_hijo1 == 0) {
			sleep(2);
			printf("Hijo[2] pid = %d y mi padre = %d\n",
				getpid(), getppid());
		}
		else {
			pid_hijo2 = fork();
			if (pid_hijo2 == 0) {
			
				printf("Hijo[3] pid = %d y mi padre = %d\n",
					getpid(), getppid());
			}


                        // Si valor es mayor a 0, este es el padre y lo mostramos
			else {
			
				sleep(5);
				printf("Padre pid = %d\n", getpid());
			}
		}
	}

	return 0;
}
