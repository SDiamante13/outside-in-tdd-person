package path.to.programming.outsideintddperson.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import path.to.programming.outsideintddperson.model.Person;

class PersonRepositoryTest {

    private final PersonRepository personRepository = new PersonRepository();

    @Test
    void save_updatesPersonEntries() {
        Person person = Person.builder()
                .personId("1")
                .firstName("Steven")
                .build();

        Person savedPerson = personRepository.save(person);

        Assertions.assertThat(savedPerson.getFirstName()).isEqualTo("Steven");
    }

    @Test
    void findAll_doesNothing() {
    }

    @Test
    void findAll_byAGivenPerson_doesNothing() {
    }
}