import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

    // Map of names to Person instances.
    private Map<String, Person> personMap = new HashMap<>();

    // Singleton instance
    private static DataStore instance = new DataStore();

    public static DataStore getInstance(){
        return instance;
    }

    // Private constructor to prevent direct instantiation
    private DataStore(){
        // Dummy data
        personMap.put("Ada", new Person("Ada", "Ada Lovelace was the first programmer.", 1815));
        personMap.put("Kevin", new Person("Kevin", "Kevin is the author of HappyCoding.io.", 1986));
        personMap.put("Stanley", new Person("Stanley", "Stanley is Kevin's cat.", 2007));
        personMap.put("Eva", new Person("Eva", "Eva is a girl from France.", 1988));
    }

    public Person getPerson(String name) {
        return personMap.get(name);
    }

    public void putPerson(Person person) {
        personMap.put(person.getName(), person);
    }

    public boolean removePerson(String name) {
        return personMap.remove(name) != null;
    }
}
