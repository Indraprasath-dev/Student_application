package com.i2i.cms.util;

/**
 * <p>
 * Utility class for common String operations.
 * </p>
 */
public class StringUtil {
    private StringUtil() {}

    /**
     * <p>
     *  Determines whether the given name is in correct format or not.
     *  Valid name should contain alphabets ranging from A-Z or a-z.
     * </p>
     * @param name 
     *        The name string to be validated.
     * @return True if the name string is in the correct format, otherwise false.
     */
    public static boolean isValidName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
