
import java.io.IOException;
import java.rmi.StubNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.SourceDataLine;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStorage {

    // Map of names to Person instances.
    private Map<String, Person> personMap = new HashMap<>();

    public DataStorage() {
        // dummy data
        personMap.put("Ada", new Person("Ada", "Ada Lovelace was the first programmer.", 1815));
        personMap.put("Kevin", new Person("Kevin", "Kevin is the author of HappyCoding.io.", 1986));
        personMap.put("Stanley", new Person("Stanley", "Stanley is Kevin's cat.", 2007));
    }

    public Person getPerson(String name) throws IOException {

        return personMap.get(name);
    }

    public String putPerson(Person person) throws IOException {

        try {
            personMap.put(person.getName(), person);
            return person.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String deletePerson(String name) throws IOException {

        try {
            Person person = personMap.remove(name);
            return person.getName();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String modifyPerson(String name, Person updatePerson) throws IOException {

        try {
            personMap.remove(name);
            personMap.put(name, updatePerson);
            return updatePerson.getName();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}