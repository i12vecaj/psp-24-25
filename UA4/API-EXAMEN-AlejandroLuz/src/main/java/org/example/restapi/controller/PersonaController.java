package org.example.restapi.controller;

import org.example.restapi.model.Persona;
import org.example.restapi.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService service;

    // Obtener todas las personas
    @GetMapping
    public List<Persona> obtenerTodas() {
        return service.obtenerTodas();
    }

    // Obtener una persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPorId(@PathVariable int id) {
        Optional<Persona> persona = service.obtenerPorId(id);
        return persona.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<Persona> crear(@RequestBody Persona persona) {
        Persona nuevaPersona = service.guardar(persona);
        return ResponseEntity.ok(nuevaPersona);
    }

    // Actualizar una persona existente
    @PutMapping("/{id}")
    public ResponseEntity<Persona> actualizar(@PathVariable int id, @RequestBody Persona persona) {
        Optional<Persona> personaExistente = service.obtenerPorId(id);

        if (personaExistente.isPresent()) {
            persona.setId(id); // Asigna el ID manualmente para evitar errores
            Persona personaActualizada = service.guardar(persona);
            return ResponseEntity.ok(personaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una persona por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        Optional<Persona> personaExistente = service.obtenerPorId(id);

        if (personaExistente.isPresent()) {
            service.eliminar(id);
            return ResponseEntity.ok("Persona eliminada correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
