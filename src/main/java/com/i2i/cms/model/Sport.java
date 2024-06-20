package com.i2i.cms.model;

import java.util.Set;

import com.i2i.cms.model.Student;

/**
 * <p>
 * Represents a student with associated information such as sport id, sport name, coach.
 * </p>
 */
public class Sport {

    private int sportId;
    private String sportName;
    private String coach;
    private Set<Student> students;
    
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
    
    public Set<Student> getStudents() {
        return students;
    }
    
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    /**
     * <p>
     * Returns a string representation of the Sport object for printing.
     * </p>
     * @return A string representation of the Sport object.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Sport Id = ").append(sportId)
                     .append(", SportName = '").append(sportName)
                     .append("', Coach = '").append(coach)
                     .append("' ");
        return stringBuilder.toString();
    }

}
