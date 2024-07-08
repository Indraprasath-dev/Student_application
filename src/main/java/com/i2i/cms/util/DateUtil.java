package com.i2i.cms.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * <p>
 * Utility class for common date operations.
 * </p>
 */
public class DateUtil {
    private DateUtil() { }

    /**
     * <p>
     * Calculates the difference in years between the given date and the current date.
     * </p>
     * @param date The date to calculate the difference from.
     * @return The number of years between the given date and the current date.
     */
    public static int calculateDifferenceOfDates(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(date, currentDate);
        return period.getYears();
    }

    /**
     * <p>
     * Validates if the given date is within a reasonable range (not more than 50 years ago and not in the future).
     * </p>
     * @param date The date to validate.
     * @return true if the date is valid within the specified range, false otherwise.
     */
    public static boolean isValidateDate(LocalDate date) {
        try {
            if ((!date.isAfter(LocalDate.now())) &&
                    (!date.isBefore(LocalDate.now().minus(50, ChronoUnit.YEARS)))) {
                return true;
            }
            return false;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
