#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
int main()
{
    // Declaración de la variable que almacenará el dato que le pediremos al usuario.
    int pedidaDeDatos = 0;

    // Obtención del dato por  parte del usuario
    printf("Introduce  un número: ");
    scanf("%d", &pedidaDeDatos);

    //Declaración de los procesos padre e hijo.
    pid_t pid, Hijo_pid;
    pid = fork();
    

    if (pid == -1) // Ha ocurrido un error
    {
        printf("No se ha podido crear el proceso hijo...");
        exit(-1);
    }
    if (pid == 0) // Nos encontramos en Proceso hijo y se muestran el dato dado por el usuario y el calculo realizado.
    {
        printf("Soy el proceso hijo \n\t Tu umero es %d, y el resultado es: %d.\n", (pedidaDeDatos), (pedidaDeDatos + 4));
    }
    else // Nos encontramos en Proceso padre
    {
        Hijo_pid = wait(NULL); // espera la finalización del proceso hijo y se muestran el dato dado por el usuario y el calculo realizado.
        printf("Soy el proceso padre:\n\t Tu umero es %d, y el resultado es: %d.\n", (pedidaDeDatos), (pedidaDeDatos - 5));
    }
    exit(0);

    /*En resumen el programa crea al inicio lo que nos devuelve el fork siendo -1
    un código de error avisando de que el proceso no se ha llegado a crear por algún fallo,
    otro caso es cuando el proceso devuelve 0, esto quiere decir que se ha creado y además
    que estamos en el proceso hijo, en caso de no ser ni -1 o 0 la otra opción es que nos
    devuelva 1, lo que significa que estamos en el padre.*/
}
