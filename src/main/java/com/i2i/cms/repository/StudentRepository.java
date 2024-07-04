package com.i2i.cms.repository;

import java.util.Optional;
import com.i2i.cms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * <p>
 * Repository interface for managing student entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * </p>
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(Integer studentId);
    boolean existsById(Integer id);
    void deleteById(Integer id);

}

