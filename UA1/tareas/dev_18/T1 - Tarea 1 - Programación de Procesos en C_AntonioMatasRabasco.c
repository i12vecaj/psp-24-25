#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <errno.h>

/*
 *  FR1: Crea unproceso.
 *  FR2: Solicitar una variable al usuario.
 *  FR3: El proceso padre resta 5 a la variable.
 *  FR4: El proceso hijo suma 4 a la variable.
 *  FR5: Muestra todos los valores por pantalla.
 */

int main() {
    int var;          
    pid_t pid;        

    // Pedir al usuario una variable
    printf("Introduce un valor entero: ");
    if (scanf("%d", &var) != 1) {
        // Control de error: si el usuario no introduce un número entero válido
        fprintf(stderr, "Error: Debe ser un número entero.\n");
        return EXIT_FAILURE;
    }

    // Crear un proceso hijo
    pid = fork(); 

    if (pid < 0) 
    
    {
        // Control de error: Si fork() falla
        perror("Error al crear el proceso hijo");
        return EXIT_FAILURE;
    } 
    else if (pid == 0) 
    
    {
        // Este es el proceso hijo
        var += 4; // Sumar 4 a la variable
        printf("Proceso hijo %d\n", getpid(), var);
        exit(EXIT_SUCCESS);
    } 
    
    else {
        // Este es el proceso padre
        wait(NULL); 
        var -= 5; //Restar 5 a la variable
        printf("Proceso padre %d\n", getpid(), var);
    }

    return EXIT_SUCCESS;
}
