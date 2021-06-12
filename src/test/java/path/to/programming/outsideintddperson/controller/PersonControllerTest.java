package path.to.programming.outsideintddperson.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import path.to.programming.outsideintddperson.model.Person;
import path.to.programming.outsideintddperson.model.PersonRequest;
import path.to.programming.outsideintddperson.model.PersonResponse;
import path.to.programming.outsideintddperson.repository.PersonRepository;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private PersonRepository mockPersonRepository;

    @InjectMocks
    private PersonController personController;

    PersonRequest personRequest = PersonRequest.builder()
            .firstName("Elon")
            .lastName("Musk")
            .build();

    Person person = Person.builder()
            .firstName("Elon")
            .lastName("Musk")
            .build();

    @BeforeEach
    void setUp() {
        List<Person> people = Arrays.asList(
                Person.builder()
                        .ssn("111223333")
                        .firstName("Elon")
                        .lastName("Musk")
                        .build(),
                Person.builder()
                        .ssn("444556666")
                        .firstName("Bruce")
                        .lastName("Willis")
                        .build()
        );

        Mockito.when(mockPersonRepository.findAll(any())).thenReturn(people);
    }

    @Test
    void personSearch_callsRepositoryFindAll() {
        ResponseEntity<PersonResponse> responseEntity = personController.personSearch(personRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Mockito.verify(mockPersonRepository).findAll(person);
    }

    @Test
    void personSearch_returnsPersonResponseEntity() {
        ResponseEntity<PersonResponse> responseEntity = personController.personSearch(personRequest);

        PersonResponse expectedPersonResponse = PersonResponse.builder()
                .ssn("*****3333")
                .firstName("Elon")
                .lastName("Musk")
                .build();

        assertThat(responseEntity.getBody()).isEqualTo(expectedPersonResponse);
    }
}