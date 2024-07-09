package com.i2i.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing fee details for creating or updating a student's fees.
 * Contains fields for tuition fee, bus fee, and hostel fee.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeeDto {
    private int tuitionFee;
    private int busFee;
    private int hostelFee;
}
