package com.i2i.cms.dto;

/**
 * <p>
 * Data Transfer Object representing sports details.
 * Contains fields for sport ID, sport name, and coach.
 * </p>
 */
public class ResponseSportDto {
    private int sportId;
    private String sportName;
    private String coach;

    public int getSportId() {
        return sportId;
    }

    public void setSportId(int sportId) {
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
