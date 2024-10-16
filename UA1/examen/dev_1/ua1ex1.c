#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>  
#include <unistd.h>  

int main()
{ 
//bucle para imprimir los hijos. anteriormente hecho con 3 funciones diferentes, no había caido en esto
    for ( int i = 0; i < 3; i++ )
        if ( fork() == 0 )
        {
            printf( "ID Hijo: %d del padre: %d\n", getpid(), getppid() );
            exit(0);
        }
    printf( "Padre: %d\n", getpid() ); // pid del proceso padre
}
