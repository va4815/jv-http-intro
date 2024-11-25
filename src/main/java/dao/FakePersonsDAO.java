package dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public record FakePersonsDAO(
        String status,
        int code,
        ArrayList<People> result
) {

}
