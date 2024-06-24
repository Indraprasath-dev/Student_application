package com.i2i.cms.model;

import javax.persistence.*;
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
    @Column(name = "grade_id")
    private int gradeId;

    @Column(name = "standard")
    private int standard;

    @Column(name = "section")
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
