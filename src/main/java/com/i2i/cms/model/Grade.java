package com.i2i.cms.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

/**
 * <p>
 * Represents a grade with associated information such as standard,
 * section and students.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @Column(name = "grade_id", columnDefinition = "VARCHAR(36)")
    private String gradeId;

    @Column(name = "standard", nullable = false)
    private int standard;

    @Column(name = "section", length = 5, nullable = false)
    private String section;

    @OneToMany(mappedBy = "grade", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Student> students;

    @PrePersist
    protected void onCreate() {
        if (null == this.gradeId) {
            this.gradeId = UUID.randomUUID().toString();
        }
    }
}
