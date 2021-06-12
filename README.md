# Person Search API

## POST /person/search

### Content Body:

```json
{
  "ssn": "111223333",
  "firstName": "Elon",
  "lastName": "Musk",
  "dateOfBirth": "06/28/1971" 
}  
```

### Response:

```json
{
  "ssn": "*****3333",
  "firstName": "",
  "lastName": "",
  "dateOfBirth": "06/28/1971",
  "address": {
    "streetAddress": "123 Main St.",
    "city": "Raleigh",
    "state": "NC"
  },
  "email": "test@test.com",
  "phoneNumber": "333-444-5555"
}
```

Notes:

Datebase entries must have a timestamp & dob in EpochSecond

### Tools

MockMvc style Feature Test with embedded H2 Database

step down into unit tests to fix a line on the acceptance test

