package com.i2i.cms.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * Data Transfer Object representing student details for creating new student records.
 * Contains fields for student's name, date of birth, grade details, fee details, and selected sports.
 * </p>
 */
public class CreateStudentDto {
    private String name;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private CreateGradeDto grade;
    private CreateFeeDto fee;
    private List<UUID> selectedSports;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
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

    public List<UUID> getSelectedSports() {
        return selectedSports;
    }

    public void setSelectedSports(List<UUID> selectedSports) {
        this.selectedSports = selectedSports;
    }
}
