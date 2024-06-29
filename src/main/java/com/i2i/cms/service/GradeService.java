package com.i2i.cms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dao.GradeDao;
import com.i2i.cms.model.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * This class manages operations related to grades, including adding new grades,
 * retrieving grades by standard and section, and retrieving grades along with
 * their associated students.
 * </p>
 */
@Service
public class GradeService {
    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);
    @Autowired
    private GradeDao gradeDao;
    
    /**
     * <p>
     * Retrieves a Grade object by checking if the specified standard and section 
     * combination is present in the database. If the combination is not present, 
     * it creates a new Grade entry.
     * </p>
     * @param standard 
     *        The standard of the grade (1 to 12).
     * @param section 
     *        The section of the grade (A, B, or C).
     * @return The Grade object corresponding to the specified standard and section.
     * @throws StudentException 
     *         If there is an error while adding the grade.
     */
    public Grade addGradeId(int standard, String section) throws StudentException {
        logger.debug("Starting addGradeId process with Standard: {}, Section: {}", standard, section);
        Grade grade = gradeDao.findGradeByStandardAndSection(standard, section);
        if (null == grade) {
            logger.debug("Grade ID not found for Standard: {}, Section: {}. Creating new Grade ID.", standard, section);
            grade = new Grade();
            grade.setStandard(standard);
            grade.setSection(section);
            gradeDao.addGradeDetail(grade);
        }
        logger.debug("Finished addGradeId process for Grade ID: {}",grade.getGradeId());
        return grade;
    }
    
    /**
     * <p>
     * Retrieves a Grade object along with the list of students associated with the specified grade ID.
     * </p>
     * @param gradeId 
     *        The ID of the grade to retrieve.
     * @return The Grade object containing the list of associated students.
     * @throws StudentException 
     *         If there is an error while retrieving the grade.
     */
    public Grade findStudentsByGradeId(int gradeId) throws StudentException {
        logger.debug("Retrieving findStudentsByGradeId with Grade ID: {}", gradeId);
        return gradeDao.retrieveStudentsByGradeId(gradeId);
    }
}
