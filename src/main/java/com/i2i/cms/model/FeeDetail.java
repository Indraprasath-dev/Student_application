package com.i2i.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 * <p>
 * Represents the fee details of a student, including fee ID, tuition fee,
 * bus fee and hostel fee.
 * </p>
 */
@Entity
@Table(name = "fee_detail")
public class FeeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id", nullable = false)
    private int feeId;

    @Column(name = "tuition_fee", nullable = false)
    private int tuitionFee;

    @Column(name = "bus_fee", nullable = false)
    private int busFee;

    @Column(name = "hostel_fee", nullable = false)
    private int hostelFee;

    @OneToOne
    @JoinColumn(name = "student_id")
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
