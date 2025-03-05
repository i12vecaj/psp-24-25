package com.api;

import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private Map<String, Person> personMap = new HashMap<>();

    private static DataStore instance = new DataStore();

    public static DataStore getInstance(){
        return instance;
    }

    private DataStore(){
        personMap.put("Ada", new Person("Ada", "Ada Lovelace fue la primera programadora.", 1815));
        personMap.put("Kevin", new Person("Kevin", "Kevin es el autor de HappyCoding.io.", 1986));
        personMap.put("Stanley", new Person("Stanley", "Stanley es el gato de Kevin.", 2007));
    }

    public Person getPerson(String name) {
        return personMap.get(name);
    }

    public void putPerson(Person person) {
        personMap.put(person.getName(), person);
    }
}
