package com.i2i.cms.dto;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing student details.
 * Contains fields for student ID, name, date of birth, grade details, fee details,
 * and selected sports.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfoDto {
    private UUID id;
    private String name;
    private LocalDate dob;
    private int age;
    private CreateGradeDto grade;
    private CreateFeeDto fee;
    private Set<String> selectedSports;
}