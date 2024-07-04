package com.i2i.cms.dto;

import java.util.Set;

/**
 * <p>
 * Data Transfer Object (DTO) representing student details.
 * Contains fields for student ID, name, date of birth, grade details, fee details,
 * and selected sports.
 * </p>
 */
public class StudentDto {
    private int id;
    private String name;
    private String dob;
    private CreateGradeDto grade;
    private CreateFeeDto fee;
    private Set<String> selectedSports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Set<String> getSelectedSports() {
        return selectedSports;
    }

    public void setSelectedSports(Set<String> selectedSports) {
        this.selectedSports = selectedSports;
    }
}
