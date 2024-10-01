#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>



int main(int argc, char const *argv[])
{
  //Unicializamos las variables numericas y le pedimos un valor al usuaro
  int numero;
  printf("\nDime un número: ");
  scanf("%d", &numero);

  pid_t pid = fork();
  //Identificamos el valor -1 como un error para darle información al usuario.
  if (pid == -1)
  {
    printf("\nError: no ha un error en el proceso de creacion del proceso");
  }
  //Identificamos el valor 0 para contarle al usuario que se encuentra en el proceso hijo.
  else if (pid == 0)
  {
    printf("\nEsteamos en el proceso hijo.\n\t %d + 4 = %d", numero, numero + 4);
  }
  //Identificamos el valor 1 con un else para mostrarle al usuario que estmaos en el proceso padre
  else{
    printf("\nEstamos en el proceso pade.\n\t %d - 5 = %d", numero, numero +5);
  }
  

  return 0;
}
