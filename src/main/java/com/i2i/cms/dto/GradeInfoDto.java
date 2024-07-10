package com.i2i.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing grade details.
 * Contains fields for grade ID, standard, and section.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GradeInfoDto {
    private String gradeId;
    private int standard;
    private String section;
}
