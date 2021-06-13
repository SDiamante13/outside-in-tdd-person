package path.to.programming.outsideintddperson.repository;

import org.springframework.stereotype.Component;
import path.to.programming.outsideintddperson.model.Person;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;
import static java.util.Collections.emptyList;

@Component
public class PersonRepository {

    private Map<String, Person> peopleEntries;

    public PersonRepository() {
        this.peopleEntries = new HashMap<>();
    }

    public Person save(Person person) {
        String personId = UUID.randomUUID().toString();
        person.setPersonId(personId);
        person.setTimeStamp(LocalDateTime.now().toEpochSecond(UTC));

        peopleEntries.put(personId, person);
        return peopleEntries.get(personId);
    }

    public List<Person> findAll() {
        return new ArrayList<>(peopleEntries.values());
    }

    public List<Person> findAll(Person personCriteria) {
        return peopleEntries.values().stream()
                .filter(getPersonPredicate(personCriteria))
                .collect(Collectors.toList());
    }

    private Predicate<? super Person> getPersonPredicate(Person personCriteria) {
        if (personCriteria.getFirstName() != null) {
            return personEntry -> personEntry.getFirstName().equals(personCriteria.getFirstName());
        } else if(personCriteria.getLastName() != null) {
            return personEntry -> personEntry.getLastName().equals(personCriteria.getLastName());
        } else {
            return Objects::isNull;
        }
    }
}
