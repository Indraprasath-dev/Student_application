package com.i2i.cms.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id", nullable = false)
    private int gradeId;

    @Column(name = "standard", nullable = false)
    private int standard;

    @Column(name = "section", length = 5, nullable = false)
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Student> students;

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGradeId() {
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

    /**
     * <p>
     * Returns a string representation of the grade object for printing.
     * </p>
     * @return A string representation of the grade object.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nGrade id : ").append(gradeId)
                     .append("\nStudent Standard : ").append(standard)
                     .append("\nStudent Section : ").append(section);
        return stringBuilder.toString();
    }
}
