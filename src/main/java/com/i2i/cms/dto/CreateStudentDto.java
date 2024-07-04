package com.i2i.cms.dto;

import java.util.List;

/**
 * <p>
 * Data Transfer Object representing student details for creating new student records.
 * Contains fields for student's name, date of birth, grade details, fee details, and selected sports.
 * </p>
 */
public class CreateStudentDto {
    private String name;
    private String dob;
    private CreateGradeDto grade;
    private CreateFeeDto fee;
    private List<Integer> selectedSports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public CreateGradeDto getGrade() {
        return grade;
    }

    public void setGrade(CreateGradeDto grade) {
        this.grade = grade;
    }

    public CreateFeeDto getFee() {
        return fee;
    }

    public void setFee(CreateFeeDto fee) {
        this.fee = fee;
    }

    public List<Integer> getSelectedSports() {
        return selectedSports;
    }

    public void setSelectedSports(List<Integer> selectedSports) {
        this.selectedSports = selectedSports;
    }
}
