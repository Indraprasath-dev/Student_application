package com.i2i.cms.controller;

import java.util.Scanner;

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
        System.out.print("Enter the Sport Name: ");
        String sportName = scanner.nextLine();
        System.out.print("Enter the Coach Name: ");
        String coach = scanner.nextLine();
        try {
            Sport sport = sportService.addSport(sportName, coach);
            System.out.println("Sport added successfully. Sport ID : " + sport.getSportId());
        } catch(StudentException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
