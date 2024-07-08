package com.i2i.cms.service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateSportDto;
import com.i2i.cms.dto.ResponseSportDto;
import com.i2i.cms.model.Sport;
import com.i2i.cms.repository.SportRepository;

/**
 * <p>
 * The SportService class provides services related to sports.
 * </p>
 */
@Service
public class SportService implements SportServiceInterface {
    private static final Logger logger = LoggerFactory.getLogger(SportService.class);

    @Autowired
    private SportRepository sportRepository;

    /**
     * <p>
     * Adds a new sport using the details from the provided CreateSportDto object.
     * </p>
     * @param createSportDto The CreateSportDto object containing details of the sport to be added.
     * @return The ResponseSportDto object representing the added sport.
     * @throws StudentException If an error occurs while adding the sport.
     */
    public ResponseSportDto addSport(CreateSportDto createSportDto) throws StudentException {
        try {
            logger.debug("Adding sport: {}", createSportDto.getSportName());
            Sport sport = new Sport();
            sport.setSportName(createSportDto.getSportName());
            sport.setCoach(createSportDto.getCoach());
            Sport savedSport = sportRepository.save(sport);
            logger.debug("Sport added successfully: {}", savedSport.getSportName());
            return mapToResponseSportDto(savedSport);
        } catch (Exception e) {
            logger.error("Error adding sport: " + createSportDto.getSportName(), e);
            throw new StudentException("Error adding sport: " + createSportDto.getSportName(), e);
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
    public Set<Sport> retrieveSports(List<UUID> selectedSports) throws StudentException {
        try {
            return sportRepository.findAllById(selectedSports).stream().collect(Collectors.toSet());
        } catch (Exception e) {
            throw new StudentException("Error retrieving sports", e);
        }
    }

    /**
     * <p>
     * Maps a Sport entity to a ResponseSportDto object.
     * </p>
     * @param sport The Sport entity to be mapped.
     * @return The ResponseSportDto object containing mapped attributes from the Sport entity.
     */
    public ResponseSportDto mapToResponseSportDto(Sport sport) {
        ResponseSportDto responseSportDto = new ResponseSportDto();
        responseSportDto.setSportId(sport.getSportId());
        responseSportDto.setSportName(sport.getSportName());
        responseSportDto.setCoach(sport.getCoach());
        return responseSportDto;
    }
}
