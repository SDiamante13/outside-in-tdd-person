package path.to.programming.outsideintddperson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonResponse {
    private String ssn;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Address address;
    private String email;
    private String phoneNumber;
}
