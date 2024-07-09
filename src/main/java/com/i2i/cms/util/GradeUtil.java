package com.i2i.cms.util;

public class GradeUtil {
    private  GradeUtil() {}

    /**
     * <p>
     *  Determines whether the given standard is in correct format or not.
     *  Valid section should contain alphabets ranging from 1-12.
     * </p>
     * @param standard
     *        The standard to be validated.
     * @return True if the standard is in valid range, otherwise false.
     */
    public static boolean isValidStandard(int standard) {
        return standard >= 1 && standard <= 12;
    }

    /**
     * <p>
     *  Determines whether the given section is in correct format or not.
     *  Valid section should contain alphabets ranging from A-C or a-c.
     * </p>
     * @param section
     *        The section to be validated.
     * @return True if the section is in the correct format, otherwise false.
     */
    public static boolean isValidSection(String section ) {
        return section.matches("[a-cA-C]+");
    }
}
