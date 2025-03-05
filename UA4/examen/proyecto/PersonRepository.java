package org.example.api;

import java.util.HashMap;
import java.util.Map;

public class PersonRepository {
    private static Map<Integer, Person> people = new HashMap<>();
    private static int nextId = 1;

    public static Person getPerson(int id) {
        return people.get(id);
    }

    public static void addPerson(Person person) {
        person.setId(nextId);
        people.put(nextId, person);
        nextId++;
    }

    public static void updatePerson(int id, Person person) {
        person.setId(id);
        people.put(id, person);
    }

    public static void deletePerson(int id) {
        people.remove(id);
    }

    public static Map<Integer, Person> getAllPeople() {
        return people;
    }
}
