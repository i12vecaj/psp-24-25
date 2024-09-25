
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdbool.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
int main()
{

    /*

    1º PARTE

    declaración y creacion del proceso padre


    */

    pid_t pid;

    pid = fork();


   /*

   2ª PARTE

   -Empezamos con las condiciones, hemos implementado un control de errores,
    el cual, nos mostrará un mensaje en caso de que pid sea -1



   */

   if (pid == -1)
   {
    printf("No se ha podido crear el proceso hijo...");
    exit(-1);
   }

   /*

   3ª PARTE

   Ahora empezamos con el control de procesos y impresion en pantalla de los procesos hijos
   Vamos a trabajar con sleep para controlar el orden de los procesos, de manera de evitar errores como:

   Hijo 1.....
   Padre...
   Hijo 2....

   (Es un ejemplo de lo que pasaria si no hiciesemos un buen control)

   */

    if (pid == 0)
    {

        sleep(3);
        printf("Soy el hijo 1, pid: %d, mi padre es : %d\n",
        getpid(), getppid());

    }
        else {


        pid = fork();
        if (pid == 0)
    {
        sleep(2);
        printf("Soy el hijo 2, mi pid: %d, mi padre es : %d\n",
        getpid(), getppid());
    }

        else

    {
        pid = fork();
        if (pid == 0)
    {
        printf("Soy el hijo 3, mi pid: %d, mi padre es : %d\n",
        getpid(), getppid());
    }
    /*

    En caso contrario a lo anterior, nos mostrará el PID del padre

    */
    else
    {
    sleep(4);
    printf("Soy el padre, mi pid es: %d\n", getpid());
    }
    }
    }
    return 0;
}
