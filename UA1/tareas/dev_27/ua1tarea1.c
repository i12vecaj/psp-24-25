#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

void main() {
  pid_t pid;
  int variable;
  int suma;
  int resultado_final;
  
  printf("Puedes introducir el valor de tu variable: ");
  scanf("%d", &variable);
  
  pid = fork();

  if (pid == -1 ) 
  {
    printf("No se ha podido crear el proceso hijo");
    exit(-1);       
  }
  
  if(pid == 0) {
      variable += 4;
      
      printf("Variable en el proceso hijo con valor de %d\n", variable);
      
      exit(0);
      
      
  } else{
      pid = wait(NULL);
      variable -= 5;
      
      printf("Variable en el proceso padre con valor de %d", variable);
      exit(1);
  }
}