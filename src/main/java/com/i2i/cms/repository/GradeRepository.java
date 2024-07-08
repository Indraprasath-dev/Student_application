package com.i2i.cms.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.cms.model.Grade;

/**
 * <p>
 * Repository interface for managing Grade entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * </p>
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, UUID> {
    Grade findGradeByStandardAndSection(int standard, String section);
    Optional<Grade> findById(UUID gradeId);
}
