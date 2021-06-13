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

import java.time.LocalDate;
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

    @BeforeEach
    void setUp() {
        Mockito.when(mockPersonRepository.findAll(any()))
                .thenReturn(Arrays.asList(
                        Person.builder()
                                .ssn("111223333")
                                .firstName("Elon")
                                .lastName("Musk")
                                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                                .build(),
                        Person.builder()
                                .ssn("444556666")
                                .firstName("Bruce")
                                .lastName("Willis")
                                .dateOfBirth(LocalDate.of(1965, 7, 28).toEpochDay())
                                .build()
                ));
    }

    @Test
    void personSearch_callsRepositoryFindAll() {
        Person personCriteria = Person.builder()
                .firstName("Elon")
                .lastName("Musk")
                .build();

        ResponseEntity<PersonResponse> responseEntity = personController.personSearch(personRequest);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Mockito.verify(mockPersonRepository).findAll(personCriteria);
    }

    @Test
    void personSearch_returnsPersonResponseEntity() {
        ResponseEntity<PersonResponse> responseEntity = personController.personSearch(personRequest);

        PersonResponse expectedPersonResponse = PersonResponse.builder()
                .ssn("*****3333")
                .firstName("Elon")
                .lastName("Musk")
                .dateOfBirth("06/28/1971")
                .build();

        assertThat(responseEntity.getBody()).isEqualTo(expectedPersonResponse);
    }
}