package org.example.restapi.service;

import org.example.restapi.model.Persona;
import org.example.restapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository repository;

    public List<Persona> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<Persona> obtenerPorId(int id) {
        return repository.findById(id);
    }

    public Persona guardar(Persona persona) {
        return repository.save(persona);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}
