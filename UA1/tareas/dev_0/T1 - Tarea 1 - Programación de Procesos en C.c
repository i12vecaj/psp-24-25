#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int num;
    pid_t pid;

    // FR2: Pedir al usuario una variable (ahora llamada num)
    printf("Introduce una variable entera (num): ");
    scanf("%d", &num);

    // FR1: Crear un proceso (uno padre y otro hijo)
    pid = fork();

    if (pid == -1) {
        // Error al crear el proceso hijo
        printf("Error al crear el proceso hijo\n");
        return 1;
    }

    if (pid == 0) {
        // FR4: Proceso hijo suma 4 a la variable num
        num += 4;
        printf("Proceso hijo (PID: %d): num + 4 = %d\n", getpid(), num);
    } else {
        // FR3: Proceso padre resta 5 a la variable num
        num -= 5;
        printf("Proceso padre (PID: %d): num - 5 = %d\n", getpid(), num);

        // Esperar a que termine el proceso hijo (FR5)
        wait(NULL);
    }

    return 0;
}