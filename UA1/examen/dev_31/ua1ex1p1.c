
// estas son las librerias que estamos usando
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

// aqui se imprime la información de cada hijo
void imprimir_informacion_hijo(int id_hijo, pid_t pid_hijo, pid_t pid_padre) {
    printf("Hijo %d: PID = %d, PID padre = %d\n", id_hijo, pid_hijo, pid_padre);
}

int main() {
    pid_t pid_padre = getpid(); 
    pid_t pid_hijo[3];  
    int i;

    //se almacenan los PIDs de los hijos y tambien el PID del proceso padre

    // Crear 3 hijos
    for (i = 0; i < 3; i++) {
        pid_hijo[i] = fork();  // se crea un nuevo proceso hijo



        if (pid_hijo[i] < 0) {
            // Error en fork
            perror("Ha habido un error al crear el proceso hijo");
            exit(EXIT_FAILURE);
        }



        if (pid_hijo[i] == 0) {
        
            imprimir_informacion_hijo(i + 1, getpid(), getppid());
            exit(0);  // el hijo termina 
        }
    }

    
    for (i = 0; i < 3; i++) {
        wait(NULL);
    }
    // el padre se espera a que terminen todos los proceso hijos

    // finalmente ahora se imprime el proceso padre
    printf("Proceso padre: PID = %d\n", pid_padre);

    return 0;
}


// al principio he hecho una funcion la cual va a aser la que imprima en la consola los PIDs de los hijos y del padre
// luego en el main he declarado las variables del padre y de los hijos, luego he creado los 3 hijos a traves de un for para que esté el codigo mas optimizado
// luego he controlado errores como el error al crear el proceso hijo y luego que la creacion del hijo termine
// finalmente el proceso padre espera a que los procesos hijos terminen y que seguidamente imprima el proceso padre

