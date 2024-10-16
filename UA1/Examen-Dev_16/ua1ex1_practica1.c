/*Examen hecho por Alberto Mármol Bello a día 15 de Ocutbre para la asignatura de Programación de servicios y procesos. 
PARTE PRÁCTICA 1/2
FR1: Haz un programa en C que genere una estructura de procesos con un PADRE y 3 HIJOS, del mismo padre se entiende - 2 puntos
FR2: Visualiza por cada hijo su identificador (si es el hijo 1, 2 ó 3), su PID y el del padre, utilizando para ello una función definida por ti a la que llamen los procesos hijos - 2 puntos
FR3: Justo antes de finalizar el programa PADRE, se debe imprimir por pantalla el PID del padre de todos una única vez. Debe hacerlo el programa PADRE - 2 puntos
FR4: Implementa el control de errores - 2 puntos
FR5: Documenta y estructura el código - 2 puntos*/

//Incluyo las librerias que me vayan a hacer falta.
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

//Creo una función para que imprima la información del proceso hijo.
void imprimirHijo(int IdHijo) {
    printf("Hola soy el hijo %d, mi PID es %d y el de mi padre es %d.\n", IdHijo, getpid(), getppid());
}

int main(){
    //Creo 3 procesos hijos.
    for (int i = 1;i <= 3; i++) {
        pid_t pid = fork(); //Se crea un nuevo proceso.

        //He creado un control de erroes por si algo sale mal, notificarlo al usuario.
        if(pid < 0){
            perror("Error al crear el proceso hijo :(");
            exit(EXIT_FAILURE);
        }

        //Si es el proceso hijo.
        if (pid == 0) {
            imprimirHijo(i); //Imprimo la infórmacion del hijo.
            exit(0); //Termina el hijo.
        }

        //Tendremos que esperar a que todos los procesos hijos terminen ya que puede llevar algun tiempo.
        for(int i = 0; i < 3; i++) {
            wait(NULL); //Con "wait(NULL)" indico al programa que espere a un hijo.
        }

        //Por último imprimo el PID del padre.
        printf("El PID del padre es %d.\n", getpid());
        
        //Termina el programa.
        return 0;

        /*En Visual Studio Code no me compila, pero desde un compilador online si que lo hace.
        Compilador: https://www.onlinegdb.com/online_c_compiler
        El resultado se debe ver algo asi (el PID va cambiando):
        Soy el hijo 1. Mi PID es 936 y el PID de mi padre es 932.
        Soy el hijo 2. Mi PID es 937 y el PID de mi padre es 932.
        Soy el hijo 3. Mi PID es 938 y el PID de mi padre es 932.
        PID del padre: 932*/
    }
}