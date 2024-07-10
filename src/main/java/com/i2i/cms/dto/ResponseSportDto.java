package com.i2i.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object representing sports details.
 * Contains fields for sport ID, sport name, and coach.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSportDto {
    private String sportId;
    private String sportName;
    private String coach;
}
