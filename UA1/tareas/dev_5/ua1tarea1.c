#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int variable;
    pid_t pid, hijo_pid;

    // Solicita un número al usuario
    printf("Introduce un número: ");
    

    if (scanf("%d", &variable) != 1) {
        printf("Error en la introducción de datos, introduce un número entero.\n");
        exit(EXIT_FAILURE);
    }

    // Crea el proceso hijo
    pid = fork();

    if (pid < 0) {
        printf("No se ha podido crear el proceso hijo.\n");
        exit(EXIT_FAILURE);
    } else if (pid == 0) {  // Proceso hijo
        printf("Soy el proceso hijo.\n");
        variable += 4;
        printf("El resultado al sumarle 4 es: %d\n", variable);
        exit(EXIT_SUCCESS); // Finaliza exitosamente
    } else {  // Proceso padre
        printf("Soy el proceso padre.\n");
        variable -= 5;
        printf("El resultado al restarle 5 es: %d\n", variable);
        
        // Espera a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre después de que el hijo terminó.\n");
    }

    return 0;
}
