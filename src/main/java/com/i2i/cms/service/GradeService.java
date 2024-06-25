package com.i2i.cms.service;

import java.util.ArrayList;
import java.util.List;

import com.i2i.cms.customexception.StudentException;
import com.i2i.cms.dao.GradeDao;
import com.i2i.cms.model.Grade;

/**
 * <p>
 * This class manages operations related to grades, including adding new grades,
 * retrieving grades by standard and section, and retrieving grades along with
 * their associated students.
 * </p>
 */
public class GradeService {
    private GradeDao gradeDao = new GradeDao();
    
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
        Grade grade = gradeDao.findGradeByStandardAndSection(standard, section);
        if (null == grade) {
            grade = new Grade();
            grade.setStandard(standard);
            grade.setSection(section);
            gradeDao.addGradeDetail(grade);
        }
        return grade;
    }
    
    /**
     * <p>
     * Retrieves a Grade object along with the list of students associated with the specified grade ID.
     * </p>
     * @param gradeId 
     *        The ID of the grade to retrieve.
     * @return The Gradec object containing the list of associated students.
     * @throws StudentException 
     *         If there is an error while retrieving the grade.
     */
    public Grade findStudentsByGradeId(int gradeId) throws StudentException {
        return gradeDao.retrieveStudentsByGradeId(gradeId);
    }
}
