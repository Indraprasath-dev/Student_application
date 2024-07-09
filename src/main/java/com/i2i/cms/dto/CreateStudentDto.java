package com.i2i.cms.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing student details for creating new student records.
 * Contains fields for student's name, date of birth, grade details, fee details, and selected sports.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentDto {
    private String name;
    private LocalDate dob;
    private CreateGradeDto grade;
    private CreateFeeDto fee;
    private List<UUID> selectedSports;
}
