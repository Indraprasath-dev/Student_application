    package com.i2i.cms.dto;

    /**
     * <p>
     * Data Transfer Object representing fee details for creating a student's fees.
     * Contains fields for tuition fee, bus fee, and hostel fee.
     * </p>
     */
    public class CreateFeeDto {
        private int tuitionFee;
        private int busFee;
        private int hostelFee;

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
    }
