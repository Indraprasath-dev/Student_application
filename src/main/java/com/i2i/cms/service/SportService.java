package com.i2i.cms.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.cms.controller.SportController;
import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.SportDto;
import com.i2i.cms.model.Sport;
import com.i2i.cms.repository.SportRepository;

/**
 * <p>
 * The SportService class provides services related to sports.
 * </p>
 */
@Service
public class SportService {
    @Autowired
    private SportRepository sportRepository;
    private static final Logger logger = LoggerFactory.getLogger(SportController.class);

    /**
     * <p>
     * Adds a new sport using the details from the provided SportDto object.
     * </p>
     * @param sportDto The SportDto object containing details of the sport to be added.
     * @return The SportDto object representing the added sport.
     * @throws StudentException If an error occurs while adding the sport.
     */
    public SportDto addSport(SportDto sportDto) throws StudentException {
        try {
            logger.debug("Adding sport: {}", sportDto.getSportName());
            Sport sport = new Sport();
            sport.setSportName(sportDto.getSportName());
            sport.setCoach(sportDto.getCoach());
            Sport savedSport = sportRepository.save(sport);
            logger.debug("Sport added successfully: {}", savedSport.getSportName());
            return mapToSportDto(savedSport);
        } catch (Exception e) {
            logger.error("Error adding sport: " + sportDto.getSportName(), e);
            throw new StudentException("Error adding sport" +sportDto.getSportName(), e);
        }
    }

    /**
     * <p>
     * Retrieves a set of sports based on the list of selected sport IDs.
     * </p>
     * @param selectedSports A list of sport IDs representing the selected sports.
     * @return A set of Sport objects matching the selected sport IDs.
     * @throws StudentException If an error occurs while retrieving the sports.
     */
    public Set<Sport> retrieveSports(List<Integer> selectedSports) throws StudentException {
        try {
            return sportRepository.findAllById(selectedSports).stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new StudentException("Error retrieving sports", e);
        }
    }

    /**
     * <p>
     * Maps a Sport entity to a SportDto object.
     * </p>
     * @param sport The Sport entity to be mapped.
     * @return The SportDto object containing mapped attributes from the Sport entity.
     */
    private SportDto mapToSportDto(Sport sport) {
        SportDto sportDto = new SportDto();
        sportDto.setSportId(sport.getSportId());
        sportDto.setSportName(sport.getSportName());
        sportDto.setCoach(sport.getCoach());
        return sportDto;
    }
}
