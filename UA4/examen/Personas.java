import java.util.HashMap;
import java.util.Map;

public class Personas {
    private Map<Integer, String> personas = new HashMap<>();

    public Personas() {
        personas.put(1, "Juan");
        personas.put(2, "Maria");
        personas.put(3, "Carlos");
    }

    public String getPersona(int id) {
        return personas.get(id);
    }

    public Map<Integer, String> getAllPersonas() {
        return personas;
    }
}
