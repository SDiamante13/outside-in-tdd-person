package path.to.programming.outsideintddperson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private String ssn;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
}
