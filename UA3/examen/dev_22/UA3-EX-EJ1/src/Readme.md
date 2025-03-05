//Revisado por JD: 20/02/2025

**¿Es más conveniente utilizar TCP o UDP en este caso? Explica tu respuesta.**

Para este caso es recomendable usar TCP, ya que queremos hacer una conexión Cliente-Servidor
y TCP es más confiable que UDP, ya que TCP garantiza la entrega de los datos, mientras que UDP no.



**¿Qué hace este programa?**

El servidor recibe un mensaje del cliente y lo imprime en pantalla, para así tener un registro del chat,
este mismo mensaje, lo podrán recibir el resto de clientes conectados al servidor.

**¿Problemas a solucionar?**

Ahora mismo el programa, cuando el cliente intenta salirse del chat, con el mensaje "Salir", este peta,
pero el servidor lo registra correctamente.
