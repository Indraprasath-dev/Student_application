package com.i2i.cms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Represents the fee details of a student, including fee ID, tuition fee,
 * bus fee and hostel fee.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fee_details")
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
}