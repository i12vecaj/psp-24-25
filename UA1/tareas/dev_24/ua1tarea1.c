#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main() {
    pid_t pid, Hijo_pid;
    int num;
    printf("Introduce un numero: ");
    scanf("%d", &num);
    pid = fork(); // Se crea una copia del proceso padre

    if (pid == -1) {
        printf("ERROR | No ha funcionado fork");
        exit(-1);
    }

    if (pid == 0) { //Proceso hijo
        num += 4;
        printf("\nEl resultado del proceso padre es: %d\n", num);
        printf("\nSe acabo");
    } else { //Proceso Padre
        num -= 5;
        printf("El resultado del proceso padre es: %d\n", num);
        Hijo_pid = wait(NULL); //Nos lleva al inicio del fork en la linea 10
    }
    
    return 0;
}