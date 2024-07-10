package com.i2i.cms.model;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Represents the sport details of a student, including sport ID, sport name,
 * and coach.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sports")
public class Sport {
    @Id
    @Column(name = "sport_id", columnDefinition = "VARCHAR(36)")
    private String sportId;

    @Column(name = "sport_name", length = 20, nullable = false)
    private String sportName;

    @Column(name = "coach", length = 30, nullable = false)
    private String coach;

    @ManyToMany(mappedBy = "sports")
    private Set<Student> students;

    @PrePersist
    protected void onCreate() {
        if (null == this.sportId) {
            this.sportId = UUID.randomUUID().toString();
        }
    }
}
