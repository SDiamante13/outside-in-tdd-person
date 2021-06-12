package path.to.programming.outsideintddperson.repository;

import org.springframework.stereotype.Component;
import path.to.programming.outsideintddperson.model.Person;

import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class PersonRepository {

    public Person save(Person person) {
        // TODO: add person to personEntries list
        return person;
    }

    public List<Person> findAll() {
        return emptyList();
    }

    public List<Person> findAll(Person person) {
        return emptyList();
    }
}
