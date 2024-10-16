/*

NOMBRE: Antonio Gomez Cuevas
FECHA: 16/10/2024

PARTE PRÁCTICA

FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos

Para evitar complicaciones con máquinas virtuales, si lo prefieres puedes utilizar el compilador online: https://www.onlinegdb.com/online_c_compiler

Notas:

Los comentarios (descriptivos y concisos) en el código ... siempre son bien.
Los nombres de las variables autodescriptivos ... siempre son bien.
Las impresión por pantalla, correctamente indentada y verticalmente espaciada ... siempre es bien.
Los warnings del presente ... son los errores del futuro.
El nombre del fichero .c a entregar debe ser: examen\dev_X\ua1ex1.c , es decir, el fichero ua1ex1.cdebe estar ubicado en tu carpeta dev_X\

*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void mostrar_info_hijo(int id_hijo, pid_t pid_hijo, pid_t pid_padre) {
    printf("Soy el hijo %d, mi PID es %d y el PID de mi padre es %d\n", id_hijo, pid_hijo, pid_padre);
}

int main() {
    // Declaramos las variables
    pid_t pid_hijo1, pid_hijo2, pid_hijo3;
    pid_t pid_padre = getpid();

    // Crear el primer hijo
    pid_hijo1 = fork();

    // Control de errores
    if (pid_hijo1 == -1) {
        perror("No se pudo crear el hijo 1");;
        exit(-1);
    }
  
    if (pid_hijo1 == 0) {
        mostrar_info_hijo(1, getpid(), pid_padre);
        exit(0);
    }

    // Crear el segundo hijo
    pid_hijo2 = fork();

    // Control de errores
    if (pid_hijo2 == -1) {
        perror("No se pudo crear el hijo 2");
        exit(-1);
    }
  
    if (pid_hijo2 == 0) {
        mostrar_info_hijo(2, getpid(), pid_padre);
        exit(0);
    }

    // Crear el tercer hijo
    pid_hijo3 = fork();

    // Control de errores
    if (pid_hijo3 == -1) {
        perror("No se pudo crear el hijo 3");
        exit(-1);
    }
  
    if (pid_hijo3 == 0) {
        mostrar_info_hijo(3, getpid(), pid_padre);
        exit(0);
    }

    // Esperar a que todos los hijos terminen
    wait(NULL);
    wait(NULL);
    wait(NULL);

    printf("Soy el padre, mi PID es %d\n", pid_padre);

    return 0;
}
