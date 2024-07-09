package com.i2i.cms.model;

import java.util.Set;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "uuid2")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "grade_id", columnDefinition = "VARCHAR(36)")
    private UUID gradeId;

    @Column(name = "standard", nullable = false)
    private int standard;

    @Column(name = "section", length = 5, nullable = false)
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
