package com.i2i.cms.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.i2i.cms.model.Student;

/**
 * <p>
 * Repository interface for managing student entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * </p>
 */
public interface StudentRepository extends JpaRepository<Student, String> {
}

