package com.i2i.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * Data Transfer Object representing fee details.
 * Contains fields for tuition fee, bus fee, and hostel fee.
 * </p>
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FeeInfoDto {
    private int tuitionFee;
    private int busFee;
    private int hostelFee;
}
