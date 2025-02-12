**¿Es más conveniente utilizar TCP o UDP en este caso? Explica tu respuesta.**

Para este caso es recomendable usar UDP, ay que no nos importa si las notificaciones, llegan en orden, o bien, que se nos pueda perder alguna por el camino.
Sino que nos interesa la eficiencia, y que sea rápido, por lo que UDP es la mejor opción.

**¿Qué hace este programa?**

El servidor manda un mensaje o alerta, según lo que queramos hacer, y este es enviado a todos los clientes que estén conectados al servidor. Para ello, hemos establecido un puerto en ambos
para que estén en constante comunicación.

**¿Problemas a solucionar?**

No sé si es un problema como tal, pero cuando el Cliente intenta salir de la aplicacion, no se cierra la comunicacion hasta que el Servidor, nos mande un mensaje, o bien, el corte la comunicacion antes.