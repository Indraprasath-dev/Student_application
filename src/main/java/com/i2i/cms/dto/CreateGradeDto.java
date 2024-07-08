package com.i2i.cms.dto;

import java.util.UUID;

/**
 * <p>
 * Data Transfer Object representing grade details for creating or updating student grades.
 * Contains fields for standard and section.
 * </p>
 */
public class CreateGradeDto {
    private UUID gradeId;
    private int standard;
    private String section;

    public void setGradeId(UUID gradeId) {
        this.gradeId = gradeId;
    }

    public UUID getGradeId() {
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
