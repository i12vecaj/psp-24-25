He hecho uso de TCP ya que es una conexion persistente y garantiza la entrega de los mensajes, en este caso el servidor manda un mensaje a todos los clientes conectados
eso si no he sido capaz de mostrar los mensajes entre los clientes

El codigo en el que me he inspirado ha sido en el ejericico de clase que propusiste hace unos dias en el cual pedias hacer un Chat en tiempo real haciendo uso de sockets,
multicastsocket e hilos.

El servidor por un lado lo dejo a la escucha de mensajes de los clientes haciendo uso de hilos, con el mlticastsocket recivo los mensajes a todos los clientes conectados,
pero no he sido capaz de mandar mensajes a todos los clientes conectados, simulando un chat.

Por otro lado el cliente se conecta al servidor y manda mensajes al servidor todos los que quieras hasta que introduzcas salir, pero no he sido capaz de recibir mensajes d
el servidor.

Para salir del chat en el cliente se debe introducir la palabra salir he puesto que se ignore si es mayusculas o minusculas, para que sea mas comodo.

Para el ejercicio he usado TCP ya que se adapta mas al problema que se plantea.

!!!!! 
Jose David como te he dicho en clase el package no me dejaba poner barra normal y he tenido que cambiar a barra baja y al cambiar el nombre de los packages ahora da error tanto en este como el 2 
