#include <stdio.h>   // Para printf, scanf
#include <stdlib.h>  // Para exit
#include <unistd.h>  // Para fork, getpid, getppid
#include <sys/wait.h> // Para wait

int main() {
    pid_t pid, hijo_pid;
    int variable;

    // FR2: Solicitar variable al usuario
    printf("Introduce una variable entera: ");
    if (scanf("%d", &variable) != 1) {
        printf("Error al leer la variable\n");
        exit(-1);  // Control de error en la entrada
    }

    // FR1: Crear un proceso (padre e hijo)
    pid = fork();
    
    // Control de errores en fork
    if (pid == -1) { 
        // Ha ocurrido un error al crear el proceso
        printf("No se ha podido crear el proceso hijo.\n");
        exit(-1);
    }
    
    if (pid == 0) {
        // Proceso hijo
        // FR4: El proceso hijo suma 4 a la variable
        variable += 4;
        printf("Proceso hijo: Valor de la variable después de sumar 4: %d\n", variable);
        exit(0); // El hijo termina
    } else {
        // Proceso padre
        // FR3: El proceso padre resta 5 a la variable
        variable -= 5;
        printf("Proceso padre: Valor de la variable después de restar 5: %d\n", variable);
        
        // Esperar la finalización del proceso hijo (FR5)
        hijo_pid = wait(NULL);
    }
    
    return 0;
}
