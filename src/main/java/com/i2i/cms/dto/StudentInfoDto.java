package com.i2i.cms.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * <p>
 * Data Transfer Object (DTO) representing student details.
 * Contains fields for student ID, name, date of birth, grade details, fee details,
 * and selected sports.
 * </p>
 */
public class StudentInfoDto {
    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
    private CreateGradeDto grade;
    private CreateFeeDto fee;
    private Set<String> selectedSports;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
