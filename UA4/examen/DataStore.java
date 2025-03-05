package org.example;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

    private Map<String, Person> personMap = new HashMap<>();

    private static DataStore instance = new DataStore();
    public static DataStore getInstance(){
        return instance;
    }

    private DataStore(){
        // Dummy data
        personMap.put("Ada", new Person("Ada", "Ada Lovelace was the first programmer.", 1815));
        personMap.put("Kevin", new Person("Kevin", "Kevin is the author of HappyCoding.io.", 1986));
        personMap.put("Stanley", new Person("Stanley", "Stanley is Kevin's cat.", 2007));
    }

    public Person getPerson(String name) {
        return personMap.get(name);
    }

    public void putPerson(Person person) {
        personMap.put(person.getName(), person);
    }
}
