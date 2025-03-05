He creado las clases que me proporcionó el tutorial y he usado Jetty para ejecutar un Servlet.
Primero, configuré un proyecto Maven en IntelliJ y añadí las dependencias de Jetty en el archivo
pom.xml.
Luego, creé la clase PersonServlet.java para manejar las solicitudes GET y POST.
Después, creé la clase JettyServer.java para configurar y ejecutar el servidor en el puerto 8080,
registrando el servlet en el proceso. Finalmente, al ejecutar JettyServer.java, el servidor corre
en localhost:8080 y puedo hacer peticiones al servlet.