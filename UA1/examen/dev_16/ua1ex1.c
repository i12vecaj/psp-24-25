/*
NOMBRE:Javier García Ruiz
FECHA: 26/10/2022



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

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>


void informacion_hijos(int numero){/*Creamos una funcion para mostrar la informacion de los hijos*/
    
    printf("Soy el hijo %d(%d, hijo de %d)\n",
            numero,getpid(), getppid());
            
}


int main() {
  /*Creamos las variables*/
    int contador=0;
    pid_t pid_p, pid_h1, pid_h2, pid_h3;
    int status1, status2, status3,status4;
  
  /*Creamos al padre*/
	if ( (pid_p=fork()) == 0 ){ 
	    /* padre (1a generacion)  */
	    contador++;/*Incrementamos el contador*/
		if ( (pid_h1=fork()) == 0 ){/*Creamos a los hijos*/
		    /* hijo (2a generacion)   */
		/*Llamamos a la funcion*/
		informacion_hijos(contador);
			
		}else{ 
		    contador++;
      /*Cortamos la señal al hijo anterior*/
    		  wait(&status2);
    		  if ( (pid_h2=fork()) == 0 ){ 
    		      /* hijo2 (2a generacion)  */
    		        informacion_hijos(contador);
    		        }else{
    		            contador++;
    		            wait(&status3);
		                if ( (pid_h3=fork()) == 0 ){ 
		                    /* hijo3 (2a generacion)  */
			                informacion_hijos(contador);
		                }else{
		                        wait(&status1);
                          /*Llamamos al padre*/
			                    printf("Soy el padre (%d)\n",
                                getpid(), getppid());
                                exit(0);
                            }
		                }               
		   
		    }
	}
	
	
	

    return 0;
}
