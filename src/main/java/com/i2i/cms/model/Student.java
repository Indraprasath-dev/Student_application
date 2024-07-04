package com.i2i.cms.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * <p>
 * Represents a student with associated information such as ID, name,
 * date of birth, grade, fee details, and sports activities.
 * </p>
 */
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "dob", nullable = false)
    private String dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fee_id")
    private FeeDetail feeDetail;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    @JsonManagedReference
    private Grade grade;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_sport",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private Set<Sport> sports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public FeeDetail getFeeDetail() {
        return feeDetail;
    }

    public void setFeeDetail(FeeDetail feeDetail) {
        this.feeDetail = feeDetail;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Set<Sport> getSports() {
        return sports;
    }

    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }

    /**
     * <p>
     * Returns a string representation of the student object for printing.
     * </p>
     * @return A string representation of the student object.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nStudent ID: ").append(id)
                .append("\nStudent Name: ").append(name)
                .append("\nStudent Date of Birth: ").append(dob)
                .append(feeDetail).append(grade)
                .append(sports);
        return stringBuilder.toString();
    }
}
