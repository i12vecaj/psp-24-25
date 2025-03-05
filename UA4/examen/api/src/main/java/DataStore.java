import java.util.HashMap;
import java.util.Map;

public class DataStore {
    private static DataStore instance;
    private Map<Integer, Person> personMap;
    private int idCounter = 1; // Para generar IDs únicos

    private DataStore() {
        personMap = new HashMap<>();
    }

    public static DataStore getInstance() {
        if (instance == null) {
            instance = new DataStore();
        }
        return instance;
    }

    public Person getPerson(int id) {
        return personMap.get(id);
    }

    public void putPerson(String name, String about, int birthYear) {
        Person person = new Person(idCounter, name, about, birthYear);
        personMap.put(idCounter, person);
        idCounter++; // Incrementamos el contador de ID
    }
}
