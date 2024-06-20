package com.i2i.cms.model;

import com.i2i.cms.model.Student;

/**
 * <p>
 * Represents the fee details of a student, including fee ID, tuition fee, 
 * bus fee and hostel fee.
 * </p>
 */
public class FeeDetail {
    private int feeId;
    private int tuitionFee;
    private int busFee;
    private int hostelFee;
    private Student student;
	
    public int getFeeId() {
        return feeId;
    }
    
    public void setFeeId(int feeId) {
        this.feeId = feeId;
    }

    public int getTuitionFee() {
        return tuitionFee;
    }
    
    public void setTuitionFee(int tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public int getBusFee() {
        return busFee;
    }
    
    public void setBusFee(int busFee) {
        this.busFee = busFee;
    }

    public int getHostelFee() {
        return hostelFee;
    }
    
    public void setHostelFee(int hostelFee) {
        this.hostelFee = hostelFee;
    }

    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * <p>
     * Returns a string representation of the feeDetail object for printing.
     * </p>
     * @return A string representation of the feeDetail object.
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nTuitionFee : ").append(tuitionFee)
                     .append(", BusFee : ").append(busFee)
                     .append(", HostelFee : ").append(hostelFee);
        return stringBuilder.toString();
    }
}
