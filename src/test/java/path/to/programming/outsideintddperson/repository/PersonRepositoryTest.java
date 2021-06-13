package path.to.programming.outsideintddperson.repository;

import org.junit.jupiter.api.Test;
import path.to.programming.outsideintddperson.model.Person;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PersonRepositoryTest {

    private final PersonRepository personRepository = new PersonRepository();

    @Test
    void save_savesNewPersonToPersonEntries_createsTimeStampAndPersonId() {
        Person person = Person.builder()
                .firstName("Elon")
                .lastName("Musk")
                .ssn("111223333")
                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                .build();

        Person savedPerson = personRepository.save(person);

        assertThat(savedPerson).usingRecursiveComparison()
                .ignoringFields("timeStamp", "personId")
                .isEqualTo(person);
        assertThat(savedPerson.getTimeStamp()).isNotZero();
        assertThat(savedPerson.getPersonId()).isNotNull();
    }

    @Test
    void findAll_returnPeopleEntries() {
        Person person = Person.builder()
                .firstName("Elon")
                .lastName("Musk")
                .ssn("111223333")
                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                .build();

        personRepository.save(person);
        List<Person> actualPeople = personRepository.findAll();

        assertThat(actualPeople.size()).isEqualTo(1);
    }

    @Test
    void findAll_byAGivenPerson_byFirstName() {
        savePeopleInDatabase();

        Person personCriteria = Person.builder()
                .firstName("Elon")
                .build();

        List<Person> matchingPeople = personRepository.findAll(personCriteria);

        assertThat(matchingPeople.size()).isEqualTo(1);
        assertThat(matchingPeople.get(0).getFirstName()).isEqualTo(personCriteria.getFirstName());
    }

    @Test
    void findAll_byAGivenPerson_byLastName() {
        savePeopleInDatabase();

        Person personCriteria = Person.builder()
                .lastName("Musk")
                .build();

        List<Person> matchingPeople = personRepository.findAll(personCriteria);

        assertThat(matchingPeople.size()).isEqualTo(1);
        assertThat(matchingPeople.get(0).getLastName()).isEqualTo(personCriteria.getLastName());
    }

    @Test
    void findAll_returnsEmptyList_whenCriteriaIsWrong() {
        savePeopleInDatabase();

        Person personCriteria = Person.builder()
                .email("")
                .build();

        List<Person> matchingPeople = personRepository.findAll(personCriteria);

        assertThat(matchingPeople.size()).isZero();
    }

    private void savePeopleInDatabase() {
        Person person1 = Person.builder()
                .firstName("Elon")
                .lastName("Musk")
                .ssn("111223333")
                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                .build();

        Person person2 = Person.builder()
                .firstName("Bruce")
                .lastName("Willis")
                .ssn("222333444")
                .dateOfBirth(LocalDate.of(1971, 7, 28).toEpochDay())
                .build();

        Person person3 = Person.builder()
                .firstName("Tom")
                .lastName("Hardy")
                .ssn("999888777")
                .dateOfBirth(LocalDate.of(1971, 8, 28).toEpochDay())
                .build();

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
    }
}