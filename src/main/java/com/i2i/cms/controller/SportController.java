package com.i2i.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.CreateSportDto;
import com.i2i.cms.dto.ResponseSportDto;
import com.i2i.cms.service.SportService;

/**
 * <p>
 * Controller class for handling sports-related operations for students.
 * </p>
 */
@RestController
@RequestMapping("/sports")
public class SportController {
    private static final Logger logger = LoggerFactory.getLogger(SportController.class);
    @Autowired
    private SportService sportService;
    
    /**
     * <p>
     * POST endpoint to add a new sport.
     * </p>
     * @param  createSportDto {@link CreateSportDto}
     * @return the HTTP response after adding a sport.
     */
    @PostMapping
    public ResponseEntity<?> addSport(@RequestBody CreateSportDto createSportDto) {
        try {
            logger.info("Adding sport");
            ResponseSportDto createdSport = sportService.addSport(createSportDto);
            logger.info("Successfully added sport: {}", createdSport.getSportId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSport);
        } catch (StudentException e) {
            logger.error("Error adding sport", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
