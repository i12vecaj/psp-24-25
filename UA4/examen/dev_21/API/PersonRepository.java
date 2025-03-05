package org.example;

import org.example.Person;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonRepository {
    private List<Person> people = new ArrayList<>();

    public PersonRepository() {
        people.add(new Person(1, "Alice"));
        people.add(new Person(2, "Bob"));
        people.add(new Person(3, "Charlie"));
    }

    public List<Person> getAllPeople() {
        return people;
    }

    public Optional<Person> getPersonById(int id) {
        return people.stream().filter(p -> p.getId() == id).findFirst();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public boolean deletePerson(int id) {
        return people.removeIf(p -> p.getId() == id);
    }
}
