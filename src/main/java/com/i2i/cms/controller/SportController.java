package com.i2i.cms.controller;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.model.Sport;
import com.i2i.cms.service.SportService;

/**
 * <p>
 * This class is responsible for managing sport records.
 * It provides functionality to add a new sport record.
 * </p>
 */
public class SportController {
    private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
    private static Scanner scanner = new Scanner(System.in);
    private SportService sportService = new SportService();
    
    /**
     * <p>
     * Adds a new sport record.
     * This method collects the sport name, and coach name from the user,
     * and then creates a new sport record.
     * </p>
     */
    public void addSport() {
        logger.info("Starting addSport process");
        System.out.print("Enter the Sport Name: ");
        String sportName = scanner.nextLine();
        System.out.print("Enter the Coach Name: ");
        String coach = scanner.nextLine();
        logger.debug("Received input - Sport Name: {}, Coach Name: {}", sportName, coach);
        try {
            Sport sport = sportService.addSport(sportName, coach);
            System.out.println("Sport added successfully with sport ID : " + sport.getSportId());
            logger.info("Sport added successfully with sport ID : {}", sport.getSportId());
        } catch(StudentException e) {
            System.out.println("Error occurred while adding sport : " + e.getMessage());
            logger.error("Error occurred while adding sport : {}", e.getMessage());
        }
        logger.info("Finished addSport process");
    }
}
