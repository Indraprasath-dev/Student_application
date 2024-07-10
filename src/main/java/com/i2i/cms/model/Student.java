package com.i2i.cms.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Represents a student with associated information such as ID, name,
 * date of birth, grade, fee details, and sports activities.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fee_id")
    private FeeDetail feeDetail;

    @ManyToOne
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_sport",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private Set<Sport> sports;

    @PrePersist
    protected void onCreate() {
        if (null == this.id) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
