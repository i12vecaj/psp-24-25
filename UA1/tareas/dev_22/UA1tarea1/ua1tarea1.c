#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
int main()
{
  pid_t pid, Padre_pid;
  int nota;    //Nota es el numero que pediremos despues al usuario
    printf ("Introduce un numero\n");
      scanf ("%d", &nota);  //Pedimos el numero con el que trabajaremos mas tarde

  pid = fork();
  //Designamos ambos procesos y el fork creara ambos y hara de pid el proceso padre

  if (pid == -1)        //Ha ocurrido un error creando el proceso padre
    {
      printf ("No se ha podido crear el proceso PADRE");
      exit (-1);
    }

      if (pid == 0)           //Nos encontramos en el Proceso padre 
    {
      nota-=5;//El proceso padre debe restar 5 y mostrar el resultado
      printf("Soy el proceso PADRE:\n\t Mi valor habiendo restando 5 es de %d.\n",
     nota);
    }
  else  //Nos encontramos en el proceso hijo
      {
      wait (NULL); //espera de la finalizacion del proceso padre
      nota+=4;            //El proceso hijo suma 4
      printf("Soy el proceso HIJO \n\t Mi valor habiendo sumado 4 es de %d.\n",
     nota);
      }
  return(0);
}