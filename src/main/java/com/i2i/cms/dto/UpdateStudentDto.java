package com.i2i.cms.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing fee details.
 * Contains fields for id, name, and dob.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDto {
    private UUID id;
    private String name;
    private LocalDate dob;
}
