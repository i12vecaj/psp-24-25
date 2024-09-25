cls
del *.class
javac -cp . Tarea1.java

javac -cp .;..\..\bin\junit-jupiter-5.8.2.jar;..\..\bin\junit-jupiter-api-5.8.2.jar;..\..\bin\apiguardian-api-1.1.2.jar Tarea1Test.java

java -cp .;..\..\bin\junit-jupiter-5.8.2.jar;..\..\bin\junit-jupiter-api-5.8.2.jar;..\..\bin\apiguardian-api-1.1.2.jar Tarea1Test
