#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>

int main() {
    int var;
    pid_t pid;

    printf("Introduce una variable entera: "); //Introducir numero
    scanf("%d", &var);



    if (pid < 0) {
        perror("Error al crear el proceso hijo");
        exit(1);
    } 
    else if (pid == 0) { //El proceso hijo se suma 4 a el valor inicial
        printf("Proceso hijo (PID: %d):\n", getpid());
        printf("Valor inicial de la variable: %d\n", var);
        var += 4;
        printf("Después de sumar 4: %d\n", var);
        exit(0); //El proceso hijo termina aquí
    } 
    else {              //El proceso Padre resta 5 al valor
        printf("Proceso padre (PID: %d):\n", getpid());
        printf("Valor inicial de la variable: %d\n", var);
        var -= 5;
        printf("Después de restar 5: %d\n", var);
    }

    return 0;
}