// T1 - Tarea 1 - Programación de Procesos en C

#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(void){
  int numero_del_usuario, valor_del_hijo, valor_del_padre;

  printf("Digame un número:\n");
  // Controlo que el usuario ingrese un numero, en caso de que ingrese otro caracter le saldrá el codigo de error
  if (scanf("%d", &numero_del_usuario) != 1) {
      fprintf(stderr, "Error: Entrada no válida.\n");
      return -1;
  }

  // Creo el proceso
  pid_t pid = fork();

  if(pid == -1){
    // Código de error
    printf("Ha ocurrido un problema en la creación del proceso");
  }
  if(pid == 0){
    // En caso de que estemos en el proceso hijo se sumará 4 
    valor_del_hijo = numero_del_usuario += 4;
    printf("El hijo le ha sumado 4 a tu número y da: %d\n", valor_del_hijo);
  }else{
    // En caso de que estemos en el padre se restará 5
    wait(NULL);
    valor_del_padre = numero_del_usuario -= 5;
    printf("El padre le ha restado 5 a tu número y da %d\n", valor_del_padre);
  }

  return 1;
}
