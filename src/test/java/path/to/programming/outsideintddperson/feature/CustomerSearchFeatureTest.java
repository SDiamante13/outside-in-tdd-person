package path.to.programming.outsideintddperson.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import path.to.programming.outsideintddperson.model.Address;
import path.to.programming.outsideintddperson.model.Person;
import path.to.programming.outsideintddperson.model.PersonRequest;
import path.to.programming.outsideintddperson.repository.PersonRepository;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerSearchFeatureTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        savePeopleInDatabase();
    }

    @Test
    @DisplayName("Person Search Endpoint returns matching person from the data store")
    void POST_personSearch() throws Exception {
        PersonRequest personRequest = PersonRequest.builder()
                .ssn("111223333")
                .firstName("Elon")
                .lastName("Musk")
                .dateOfBirth("06/28/1971")
                .build();


        mockMvc.perform(post("/person/search")
                .content(mapper.writeValueAsString(personRequest))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ssn", is("*****3333")))
                .andExpect(jsonPath("$.firstName", is("Elon")))
                .andExpect(jsonPath("$.lastName", is("Musk")))
                .andExpect(jsonPath("$.dateOfBirth", is("06/28/1971")))
                .andExpect(jsonPath("$.address.streetAddress", is("123 Main St.")))
                .andExpect(jsonPath("$.address.city", is("Raleigh")))
                .andExpect(jsonPath("$.address.state", is("NC")))
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andExpect(jsonPath("$.phoneNumber", is("333-444-5555")));
    }

    private void savePeopleInDatabase() {
        Person person1 = Person.builder()
                .firstName("Elon")
                .lastName("Musk")
                .ssn("111223333")
                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                .address(Address.builder()
                        .streetAddress("123 Main St.")
                        .city("Raleigh")
                        .state("NC")
                        .build())
                .email("test@test.com")
                .phoneNumber("333-444-5555")
                .build();

        Person person2 = Person.builder()
                .firstName("Bruce")
                .lastName("Willis")
                .ssn("222333444")
                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                .build();

        Person person3 = Person.builder()
                .firstName("Tom")
                .lastName("Hardy")
                .ssn("999888777")
                .dateOfBirth(LocalDate.of(1971, 6, 28).toEpochDay())
                .build();

        personRepository.save(person1);
        personRepository.save(person2);
        personRepository.save(person3);
    }
}
