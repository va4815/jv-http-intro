package dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FakePersonsDAO(
        int id,
        String firstname,
        String lastname,
        String email
) {

}
