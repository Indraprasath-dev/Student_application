package com.i2i.cms.dto;

/**
 * <p>
 * Data Transfer Object representing grade details.
 * Contains fields for grade ID, standard, and section.
 * </p>
 */
public class GradeInfoDto {
    private int gradeId;
    private int standard;
    private String section;

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGradeId() {
        return gradeId;
    }

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
