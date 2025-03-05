package org.example;

import org.example.Person;
import org.example.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonRepository repository;

    public PersonController() {
        this.repository = new PersonRepository();
    }

    @GetMapping
    public List<Person> getAllPeople() {
        return repository.getAllPeople();
    }

    @GetMapping("/{id}")
    public Optional<Person> getPersonById(@PathVariable int id) {
        return repository.getPersonById(id);
    }

    @PostMapping
    public void addPerson(@RequestBody Person person) {
        repository.addPerson(person);
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        return repository.deletePerson(id) ? "Deleted successfully" : "Person not found";
    }
}
