#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t pid;
    int num;

    printf("Introduzca un número: ");
    scanf("%d", &num);

    pid = fork(); //Crea el proceso hijo.

    if (pid == -1) { //Error al crear el fork.
        printf("No se pudo crear el proceso.\n");
        exit(-1);
    }

    if (pid == 0) {
        printf("Proceso Hijo, PID: %d. Sumando 4 a %d, el resultado es: %d.\n", getpid(), num, num + 4);
    } else {
        wait(NULL); // Espera a que termine el hijo
        printf("Proceso Padre, PID: %d. Restando 5 a %d, el resultado es: %d.\n", getpid(), num, num - 5);
    }
    return 0;
}