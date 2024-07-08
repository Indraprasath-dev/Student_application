package com.i2i.cms.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

/**
 * <p>
 * Represents a grade with associated information such as standard,
 * section and students.
 * </p>
 */
@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "grade_id", updatable = false, nullable = false)
    private UUID gradeId;

    @Column(name = "standard", nullable = false)
    private int standard;

    @Column(name = "section", length = 5, nullable = false)
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonBackReference
    private Set<Student> students;

    public void setGradeId(UUID gradeId) {
        this.gradeId = gradeId;
    }

    public UUID getGradeId() {
        return gradeId;
    }

    public void setStandard(int standard) {
        this.standard = standard;
    }

    public int getStandard() {
        return standard;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Student> getStudents() {
        return students;
    }
}
