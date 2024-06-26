package com.i2i.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.Set;

/**
 * <p>
 * Represents a student with associated information such as sport id, sport name, coach.
 * </p>
 */
@Entity
@Table(name = "sport")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sport_id", nullable = false)
    private int sportId;

    @Column(name = "sport_name", length = 20, nullable = false)
    private String sportName;

    @Column(name = "coach", length = 30, nullable = false)
    private String coach;

    @ManyToMany(mappedBy = "sports")
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
