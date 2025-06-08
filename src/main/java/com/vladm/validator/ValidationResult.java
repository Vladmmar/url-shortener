package com.vladm.validator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ValidationResult {

    private final List<Error> errors = new ArrayList<>();

    public void add(Error error) {
        errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }
}
