package com.i2i.cms.dto;

import java.util.UUID;
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
    private UUID sportId;
    private String sportName;
    private String coach;
}
