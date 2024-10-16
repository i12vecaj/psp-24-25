/*

NOMBRE:
FECHA:

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
// Etiquetas cogidas del ejercicio de clase


void mostrar_informacion_hijo(int num_hijo, pid_t pid_padre) {
    printf("Soy el hijo %d. Mi PID es %d y el PID de mi padre es %d\n", num_hijo, getpid(), pid_padre);
}

int main() {// Guardamos el PID del proceso padre
    pid_t pid_padre = getpid(); 
    pid_t pid_hijo;
    int num_hijos = 3;  

    // Creamos un nuevo proceso hijo
    for (int i = 1; i <= num_hijos; i++) {
        pid_hijo = fork();  
      
 // Control de errores
        if (pid_hijo < 0) { 
            perror("Error al crear proceso hijo");
            exit(EXIT_FAILURE);
        } else if (pid_hijo == 0) {  
            mostrar_informacion_hijo(i, pid_padre);  
            exit(0);  //
        }
    }

    // Metemos funcion wait para que no salga el PID padre antes que los hijos
    for (int i = 1; i <= num_hijos; i++) {
        wait(NULL);  
    }

    // El padre imprime PID antes terminar el programa
    printf("Soy el proceso PADRE. Mi PID es %d\n", pid_padre);

    return 0; 
}
