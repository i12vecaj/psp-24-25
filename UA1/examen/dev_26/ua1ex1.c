#include <sys/types.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>

int main(int argc, char *argv[])
{
	pid_t pid;
        int status;  

	if ( (pid=fork()) == 0 ) // Los tres hijos numerados en orden dirn de uno en uno quienes son y su id 
	{ 
   	  printf("Soy el hijo 1 (%d, hijo de %d)\n", getpid(),getppid());
	}
	else
	if ( (pid=fork()) == 0 )
	{ 
   	  printf("Soy el hijo 2 (%d, hijo de %d)\n", getpid(),getppid());
	}
	else
	if ( (pid=fork()) ==  0 )
	{ 
   	  printf("Soy el hijo 3 (%d, hijo de %d)\n", getpid(),getppid());
	}
	else
	{ // proceso padre esta alfinal esperando a que sus tres hijos digan su id
          wait(&status);
	  printf("Soy el padre \n", getpid(),getppid()); // (%d, hijo de %d) Poner esto no se te olvie quitarlo
	}

	return 0;
}