package com.i2i.cms.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sport_id", nullable = false)
    private UUID sportId;

    @Column(name = "sport_name", length = 20, nullable = false)
    private String sportName;

    @Column(name = "coach", length = 30, nullable = false)
    private String coach;

    @ManyToMany(mappedBy = "sports")
    private Set<Student> students;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
