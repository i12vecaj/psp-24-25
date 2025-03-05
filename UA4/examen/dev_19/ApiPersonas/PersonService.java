package main.com.example.personapi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonService {
    private List<Person> persons = new ArrayList<>();
    private int nextId = 1;

    // Constructor que agrega algunas personas de ejemplo
    public PersonService() {
        persons.add(new Person(nextId++, "Ada", "This contains \" a quote", 1815));
        persons.add(new Person(nextId++, "Alan", "A brilliant mathematician", 1910));
    }

    // Obtener todas las personas
    public List<Person> getAllPersons() {
        return persons;
    }

    // Obtener una persona por su ID
    public Optional<Person> getPersonById(int id) {
        return persons.stream().filter(p -> p.getId() == id).findFirst();
    }

    // Crear una nueva persona
    public Person createPerson(String name, String about, int birthYear) {
        Person person = new Person(nextId++, name, about, birthYear);
        persons.add(person);
        return person;
    }

    // Eliminar persona por ID
    public boolean deletePerson(int id) {
        return persons.removeIf(p -> p.getId() == id);
    }
}
