// Revisado por JD: 19/02/25

En este caso he usado TCP ya que necesitaba que los implicados se conociesen para poder hacer la conectividad bien
Para usar los archivos primero hay que poner a funcionar el Servidor.java, y luego arrancar cuantos clientes se necesiten para el chat, si no, al no haber conexión no puede arrancar el cliente
la solución la he encontrado en Stackoverflow y esta en el uso de esta linea: private  static Set<Socket> clientes = Collections.synchronizedSet(new HashSet<>());
lo que me ha permitido crear una colección de los usuarios que se iban conectando
