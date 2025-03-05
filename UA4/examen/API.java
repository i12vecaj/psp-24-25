import static spark.Spark.*;
import com.google.gson.JsonObject;

public class Main {
    public static void main(String[] args) {
        // Mensaje de inicio
        System.out.println("Hello world!");


        port(4567);


        get("/hello", (req, res) -> {
            res.type("application/json");
            JsonObject json = new JsonObject()
            json.addProperty("message", "¡Hola, API funcionando!");
            return json.toString();
        });


        System.out.println("Servidor corriendo en http://localhost:4567/hello");
    }
}
