package com.i2i.cms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dto.SportDto;
import com.i2i.cms.service.SportService;

/**
 * <p>
 * Controller class for handling sports-related operations for students.
 * </p>
 */
@RestController
@RequestMapping("/cms/api/v1/students")
public class SportController {
    @Autowired
    private SportService sportService;
    private static final Logger logger = LoggerFactory.getLogger(SportController.class);

    /**
     * <p>
     * POST endpoint to add a new sport.
     * </p>
     * @param sportDto The SportDto object containing sport details to be added.
     * @return the HTTP response after adding a sport.
     */
    @PostMapping("/add-sports")
    public ResponseEntity<?> addSport(@RequestBody SportDto sportDto) {
        try {
            logger.info("Adding sport");
            SportDto createdSport = sportService.addSport(sportDto);
            logger.info("Successfully added sport: {}", createdSport.getSportId());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSport);
        } catch (StudentException e) {
            logger.error("Error adding sport", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
