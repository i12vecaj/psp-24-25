#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void metodoMostrarPID(pid_t, pid_t, int);

int main(void){
  //Declaro un bucle donde instanciare todos los procesos
  for(int i = 0; i < 3 ; i++){
    pid_t pid = fork();

    if(pid == -1){ // En caso de que no se cree un hijo por lo que sea mando este error
      perror("Error al crear proceso");
    }
    if(pid == 0){// En caso de que nos encontremos en el hijo mostramos el método para mostrar los pid
      metodoMostrarPID(getpid(), getppid(), i);
      exit(0); // Finalizo el proceso que está corriendo actualmente con este método
    }
    printf("\n -----------------------------------------------\n");
  }

// esperamos con este bucle que finalizen todos los procesos hijo y poder mostrar al padre
  for(int i = 0; i < 3; i++){
    wait(NULL);
  }
  printf("\n -----------------------------------------------\n");
  printf("Soy el padre y tengo el pid: %d\n", getpid());

  return 0;
}
// Método que recibe los pid del hijo y padre, además del identificador del
void metodoMostrarPID(pid_t pidHijo, pid_t pidPadre, int i){
  printf("Soy el proceso hijo %d\n", (i+1));
  printf("Tengo PID: %d\n", pidHijo);
  printf("Mi padre tiene el PID %d\n", pidPadre);
}