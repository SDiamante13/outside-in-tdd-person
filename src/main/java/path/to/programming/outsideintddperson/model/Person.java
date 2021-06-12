package path.to.programming.outsideintddperson.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Person {

    private String personId;
    private long timeStamp;
    private String ssn;
    private String firstName;
    private String lastName;
    private Long dateOfBirth;
    private Address address;
    private String email;
    private String phoneNumber;
}
