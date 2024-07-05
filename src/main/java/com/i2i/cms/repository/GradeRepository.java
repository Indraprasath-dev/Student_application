package com.i2i.cms.repository;

import com.i2i.cms.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Repository interface for managing Grade entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * </p>
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Grade findGradeByStandardAndSection(int standard, String section);
    Grade findById(int gradeId);
}
