package com.i2i.cms.model;

import java.util.Date;
import java.util.Set;

import com.i2i.cms.model.FeeDetail;
import com.i2i.cms.model.Grade;
import com.i2i.cms.model.Sport;
import com.i2i.cms.util.DateUtil;

/**
 * <p>
 * Represents a student with associated information such as ID, name,
 * date of birth, grade, fee details, and sports activities.
 * </p>
 */
public class Student {
    private int id;
    private String name;
    private Date dob;
    private FeeDetail feeDetail;
    private Grade grade;
    private Set<Sport> sports;
        
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public FeeDetail getFeeDetail() {
        return feeDetail;
    }       
    public void setFeeDetail(FeeDetail feeDetail) {
        this.feeDetail = feeDetail;
    }
    
    public Grade getGrade() {
        return grade;
    }
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    
    public Set<Sport> getSports() {
        return sports;
    }
    public void setSports(Set<Sport> sports) {
        this.sports = sports;
    }
 
    /**
     * <p>
     * Returns a string representation of the student object for printing.
     * </p>
     * @return A string representation of the student object.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nStudent ID: ").append(id)
                     .append("\nStudent Name: ").append(name)
                     .append("\nStudent Date of Birth: ").append(dob)
                     .append("\nAge: ").append(DateUtil.calculateDifferenceOfDates(dob))
                     .append("\nGrade: ").append(grade)
                     .append("\nFee Details: ").append(feeDetail)
                     .append("\nList of sports: ").append(sports); 
        return stringBuilder.toString();
    }
}
