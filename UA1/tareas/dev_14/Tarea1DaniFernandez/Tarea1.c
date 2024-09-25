#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

// Author: Daniel Fernandez Balsera

void main()
{

int numero;
// Introducimos el primer valor numerico

printf("Introduzca un numero: ");
scanf("%d",&numero);

// declaramos dos variables como un proceso

pid_t pid, Hijo_pid;

// duplicamos el proceso

pid = fork();

// Primer control de errores, si el valor que tiene pid, es menor que 1, es porque no se ha podido generar ningun proceso hijo

if (pid == -1)
{

    printf("\nNo se ha podido crear el proceso hijo..");
    
}

// Segundo control de errores, si el valor de pid es 0, te suma 4 al numero introducido y te indica el PID del hijo y el padre
if (pid == 0)
{

numero = numero  + 4;
printf("Soy el proceso hijo \n\t Mi PID es %d, El PID de mi padre es: %d.\n", getpid(), getppid());
printf("\n Valor: %d",numero);
printf("\n");

}

// Ultimo control de errores, si no se diese ninguno de los anteriores casos, te indica el PID principal, el del padre y el del hijo
// tambien resta 5 al valor introducido
else
{

wait(NULL);
   printf("\n Soy el proceso padre:\n\t Mi PID es %d, El PID de mi padre es: %d.\n\t Mi hijo: %d terminó.\n",  getpid(), getppid(), pid);          
   numero = numero - 5;
printf("\n Valor: %d",numero,"\n");
  printf("\n");
   }

}





