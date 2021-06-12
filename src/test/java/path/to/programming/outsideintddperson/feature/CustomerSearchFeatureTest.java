package path.to.programming.outsideintddperson.feature;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerSearchFeatureTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Customer Search Endpoint")
    void POST_customerSearch() {


    }
}
