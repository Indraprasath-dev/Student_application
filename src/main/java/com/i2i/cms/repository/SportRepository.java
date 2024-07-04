package com.i2i.cms.repository;

import com.i2i.cms.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Repository interface for managing sport entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * </p>
 */
@Repository
public interface SportRepository extends JpaRepository<Sport, Integer> {

}
