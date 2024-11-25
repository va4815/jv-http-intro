package dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record People (
    int id,
    String firstname,
    String lastname,
    String email,
    Address address
    ) {}
