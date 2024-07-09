package com.i2i.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing sport details for creating a student sports.
 * Contains fields for sport name and coach.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSportDto {
    private String sportName;
    private String coach;
}
