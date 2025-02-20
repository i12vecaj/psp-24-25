He usado UDP puesto que al pedir que una conexion no persistente y que no se garantice la entrega de los mensajes, UDP es la mejor opcion

Para este ejercicio ha sido un poco mezcla de ejercicios por un lado he reutilizado el MulticastSocket del ejercicio 1 y por otro lado el ejercicio de la presentacion

el funcionamiento es el siguiente
1. El servidor crea un MulticastSocket y se une a un grupo multicast, en este caso el grupo aunque sea UDP la tecnologia que he implementaod tengo que hacer uso de ips y puertos
   por el multicast, sino no habria comunicacion entre los miembros del grupo.

   Desde el servidor es desde donde se manda las alertas, en este caso el servidor manda un mensaje a todos los miembros.

2. Los miembros: en este caso he hecho uso de la creacion de una clase donde esten todos los metodos como hemos hecho en el ejercicio de la presentacion
   Estos solo escuchan el mensaje que manda el servidor y lo imprimen por pantalla, en este caso he hecho uso de un bucle infinito para que esten escuchando
   pero si el servidor manda la alerta SALIR estos tanto los miembros como el servidor se paran y cierran el socket



