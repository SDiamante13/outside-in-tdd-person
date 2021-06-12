package path.to.programming.outsideintddperson.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import path.to.programming.outsideintddperson.model.Person;
import path.to.programming.outsideintddperson.model.PersonRequest;
import path.to.programming.outsideintddperson.model.PersonResponse;
import path.to.programming.outsideintddperson.repository.PersonRepository;

import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/person/search")
    public ResponseEntity<PersonResponse> personSearch(@RequestBody PersonRequest personRequest) {
        List<Person> people = personRepository.findAll(convertRequestToEntity(personRequest));
        return ResponseEntity.ok(mapToPersonResponse(people));
    }

    private Person convertRequestToEntity(PersonRequest personRequest) {
        return Person.builder()
                .ssn(personRequest.getSsn())
                .firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName())
                .build();
    }

    private PersonResponse mapToPersonResponse(List<Person> people) {
        return PersonResponse.builder()
                .ssn(maskSSN(people.get(0).getSsn()))
                .firstName(people.get(0).getFirstName())
                .lastName(people.get(0).getLastName())
                .dateOfBirth(people.get(0).getDateOfBirth() != null ? Long.toString(people.get(0).getDateOfBirth()) : null)
                .address(people.get(0).getAddress())
                .email(people.get(0).getEmail())
                .phoneNumber(people.get(0).getPhoneNumber())
                .build();
    }

    private String maskSSN(String ssn) {
        return "*****" + ssn.substring(5, 9);
    }
}
