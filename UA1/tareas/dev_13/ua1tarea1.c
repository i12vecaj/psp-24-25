#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    int numero;
    pid_t pid;

    printf("Ingrese un número entero: ");
    if (scanf("%d", &numero) != 1) {
        fprintf(stderr, "Error: entrada no válida.\n");
        return EXIT_FAILURE;
    }

    pid = fork();
    if (pid < 0) {
        perror("Error al crear el proceso");
        return EXIT_FAILURE;
    }

    if (pid == 0) {
        numero += 4;
        printf("Proceso hijo: Valor después de sumar 4: %d\n", numero);
        exit(numero);
    } else {
        numero -= 5;
        printf("Proceso padre: Valor después de restar 5: %d\n", numero);

        int estado;
        waitpid(pid, &estado, 0);
        
        if (WIFEXITED(estado)) {
            int resultado_hijo = WEXITSTATUS(estado);
            printf("Proceso hijo terminó con el valor: %d\n", resultado_hijo);
        } else {
            fprintf(stderr, "El proceso hijo no terminó correctamente.\n");
        }
    }

    printf("Valor final en el proceso padre: %d\n", numero);
    
    return EXIT_SUCCESS;
}
