import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class PersonaControlador {
   
    @GetMapping("/personas")
    public List<Persona> obtenerTodasLasPersonas() {
        return PersonaRepositorio.obtenerTodas();
    }
    
    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable int id) {
        Persona persona = PersonaRepositorio.obtenerPorId(id);
        if(persona == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(persona);
    }
    
    @PostMapping("/personas")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        Persona nuevaPersona = PersonaRepositorio.agregarPersona(persona);
        URI ubicacion = URI.create("/personas/" + nuevaPersona.getId());
        return ResponseEntity.created(ubicacion).body(nuevaPersona);
    }
    
    @DeleteMapping("/personas/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable int id) {
        boolean eliminado = PersonaRepositorio.eliminarPersona(id);
        return eliminado ? 
            ResponseEntity.noContent().build() : 
            ResponseEntity.notFound().build();
    }
}