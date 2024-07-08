package com.i2i.cms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.i2i.cms.model.Sport;

/**
 * <p>
 * Repository interface for managing sport entities in the database.
 * Extends JpaRepository to inherit basic CRUD operations.
 * </p>
 */
@Repository
public interface SportRepository extends JpaRepository<Sport, UUID> {
}
