package com.i2i.cms.dto;

/**
 * <p>
 * Data Transfer Object representing grade details for creating or updating student grades.
 * Contains fields for standard and section.
 * </p>
 */
public class CreateGradeDto {
    private int standard;
    private String section;

    public int getStandard() {
        return standard;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
