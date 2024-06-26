package com.i2i.cms.service;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dao.SportDao;
import com.i2i.cms.model.Sport;

/**
 * <p>
 * The SportService class provides services related to sports enrollments for students.
 * It interacts with the SportDao to manage the relationship between students and 
 * their sports activities.
 * </p>
 */
public class SportService {
    private static final Logger logger = LoggerFactory.getLogger(SportService.class);
    private SportDao sportDao = new SportDao();
    
    /**
     * <p>
     * Adds a new sport record.
     * </p>
     * @param sportName 
     *        The name of the sport to be added.
     * @param coach 
     *        The name of the coach for the sport.
     * @throws StudentException 
     *         If there is any error during the insertion process.
     */
    public Sport addSport(String sportName, String coach) throws StudentException {
        logger.debug("Processing Adding new sport - Sport Name: {}, Coach: {}", sportName, coach);
        Sport sport = new Sport();
        sport.setSportName(sportName);
        sport.setCoach(coach);
        return sportDao.insertSportDetail(sport);
    }
    
    /**
     * <p>
     * Retrieves sports based on a list of selected sport IDs.
     * </p>
     * @param selectedSports 
     *        A list of sport IDs representing the sports to retrieve.
     * @return A set of Sport objects that match the selected sport IDs.
     * @throws StudentException 
     *         If there is any error during the retrieval process.
     */
    public Set<Sport> retrieveSports(List<Integer> selectedSports) throws StudentException {
        logger.debug("Retrieving Sports with selected sport IDs: {}", selectedSports);
        return sportDao.retrieveSports(selectedSports);
    }
}
