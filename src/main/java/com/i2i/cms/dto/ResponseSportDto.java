package com.i2i.cms.dto;

import java.util.UUID;

/**
 * Data Transfer Object (DTO) representing sports details.
 * Contains fields for sport ID, sport name, and coach.
 */
public class ResponseSportDto {
    private UUID sportId;
    private String sportName;
    private String coach;

    public UUID getSportId() {
        return sportId;
    }

    public void setSportId(UUID sportId) {
        this.sportId = sportId;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
}
