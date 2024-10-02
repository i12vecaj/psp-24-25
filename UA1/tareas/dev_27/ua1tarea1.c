#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main() {
  pid_t pid;
  int variable;
  int suma;
  int resultado_final;
  
  printf("**BIENVENIDO**\n");
  printf("-Hola!! me puedes introducir el valor de tu variable: ");
  scanf("%d", &variable);
  
  //Creamos un clon de el proceso padre(main)
  pid = fork();

  //Aplicamos un control de errores
  if (pid == -1 ) 
  {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);       
  }
  
  //Comprobamos si estamos en el proceso hijo de ser asi le sumamos a variable 4
  if(pid == 0) {
      variable += 4;
      
      printf("Variable en el proceso hijo con valor de %d\n", variable);
      
      exit(0);
      
      
  } else{
      //Comprobamos si estamos en el proceso padre de ser asi le restamos a variable 5
      
      /* Mediante wait(NULL) lo que hacemos es esperar a que el proceso hijo termine y luego
      *  ejecutar el proceso padre
      */
      pid = wait(NULL);
      variable -= 5;
      
      printf("Variable en el proceso padre con valor de %d", variable);
      printf("\n\t**FINALIZADO**");
      exit(1);
  }
}