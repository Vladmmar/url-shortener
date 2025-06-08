package com.vladm.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@UtilityClass
public class LocalDateFormatter {

    private static final String PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDate format(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public boolean isValid(String date) {
        var today = LocalDate.now();

        try {
            return Optional.ofNullable(date)
                    .map(LocalDateFormatter::format)
                    .filter(localDate -> !localDate.isAfter(today))
                    .filter(localDate -> {
                        // check if user is older than the minimum allowed age to use the app and younger than 120 years
                        var years =Period.between(localDate, today).getYears();
                        return years <= 120 && years >= AgePolicyUtil.MINIMAL_AGE;
                    })
                    .isPresent();
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
