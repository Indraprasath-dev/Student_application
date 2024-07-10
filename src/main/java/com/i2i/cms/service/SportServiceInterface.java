package com.i2i.cms.service;

import java.util.List;
import java.util.Set;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateSportDto;
import com.i2i.cms.dto.ResponseSportDto;
import com.i2i.cms.model.Sport;

/**
 * <p>
 * Interface for Sport Service operations.
 * Provides methods to add sports, retrieve sports, and map sports to ResponseSportDto.
 * </p>
 */
public interface SportServiceInterface {

    /**
     * <p>
     * Adds a new sport using the specified CreateSportDto.
     * </p>
     * @param createSportDto the DTO containing sport details
     * @return the added ResponseSportDto object
     * @throws StudentException if there is an error adding the sport
     */
    ResponseSportDto addSport(CreateSportDto createSportDto) throws StudentException;

    /**
     * <p>
     * Retrieves a set of Sport objects based on the specified list of UUIDs.
     * </p>
     * @param selectedSports the list of UUIDs representing selected sports
     * @return a set of Sport objects
     * @throws StudentException if there is an error retrieving the sports
     */
    Set<Sport> retrieveSports(List<String> selectedSports) throws StudentException;
}
