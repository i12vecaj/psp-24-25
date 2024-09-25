import java.util.HashMap;
import java.util.Map;

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

	//Map of names to Person instances.
	private Map<Integer, Person> personMap = new HashMap<>();
	
	public DataStore(){
		//dummy data
		personMap.put(1, new Person(1,"Ada", "Ada Lovelace was the first programmer.", 1815));
		personMap.put(2, new Person(2, "Kevin", "Kevin is the author of HappyCoding.io.", 1986));
		personMap.put(3, new Person(3, "Stanley", "Stanley is Kevin's cat.", 2007));
	}

	public Person getPerson(int id) {
		return personMap.get(id);
	}

	public Person getPerson(String name) {
		Person persona = new Person();
		for(int i=1; i<=personMap.size();i++){
			if((persona=getPerson(i)).getName().equals(name)){
				return persona;
			}
		}
		return null;
	}

	public void putPerson(Person person) {
		personMap.put(person.getId(), person);
	}

    public void changePerson(int id, Person nuevaPersona){
        personMap.replace(id, nuevaPersona);
    }

    public void deletePerson(String personaBorrada){
        personMap.remove(personaBorrada);
    }

}
