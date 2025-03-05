import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PersonaRepositorio {
    private static final AtomicInteger idCounter = new AtomicInteger(1);
    private static final List<Persona> personas = new ArrayList<>();
    
    static {
        // Datos iniciales de ejemplo
        agregarPersona(new Persona(0, "Ana", 30));
        agregarPersona(new Persona(0, "Carlos", 25));
    }
    
    public static List<Persona> obtenerTodas() {
        return new ArrayList<>(personas);
    }
    
    public static Persona obtenerPorId(int id) {
        return personas.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public static Persona agregarPersona(Persona persona) {
        persona.setId(idCounter.getAndIncrement());
        personas.add(persona);
        return persona;
    }
    
    public static boolean eliminarPersona(int id) {
        return personas.removeIf(p -> p.getId() == id);
    }
}