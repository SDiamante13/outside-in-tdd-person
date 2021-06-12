package path.to.programming.outsideintddperson.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import path.to.programming.outsideintddperson.model.PersonRequest;

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
}
