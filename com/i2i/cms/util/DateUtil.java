package com.i2i.cms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * @param date
     *        The date to calculate the difference from.
     * @return The number of years between the given date and the current date.
     */
    public static int calculateDifferenceOfDates(Date date) {
        Date currentDate = new Date();
        return currentDate.getYear() - date.getYear();
    }

    /**
     * <p>
     * Parses and validates the given date string in the format of "yyyy-MM-dd".
     * </p>
     * @param dateStr
     *        The date string to be validated.
     * @return The parsed Date object if the date string is valid, or null if the format is invalid.
     */
    public static Date ensureValidDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setLenient(false); 
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
}
