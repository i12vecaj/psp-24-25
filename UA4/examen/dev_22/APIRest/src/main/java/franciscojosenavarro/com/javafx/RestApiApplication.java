package franciscojosenavarro.com.javafx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RestController
@RequestMapping("/api/persons")
public class RestApiApplication {

    private final Map<Integer, String> persons = new HashMap<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @GetMapping
    public Map<Integer, String> getPersons() {
        return persons;
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable int id) {
        return persons.getOrDefault(id, "Person not found");
    }

    @PostMapping
    public String addPerson(@RequestBody String name) {
        int id = counter.getAndIncrement();
        persons.put(id, name);
        return "Added person with ID: " + id;
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable int id) {
        if (persons.remove(id) != null) {
            return "Deleted person with ID: " + id;
        } else {
            return "Person not found";
        }
    }
}