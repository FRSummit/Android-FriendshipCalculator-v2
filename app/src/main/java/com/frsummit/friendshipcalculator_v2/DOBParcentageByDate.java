package com.frsummit.friendshipcalculator_v2;

public class DOBParcentageByDate {
    NameParcentageByNumber nameParcentageByNumber = new NameParcentageByNumber();

    public int getBODParcentage(int date_1_dd, int date_1_mm, int date_1_yy, int date_2_dd, int date_2_mm, int date_2_yy) {
        int parcentage = 0;

        int dayParcentage = date_1_dd + date_2_dd;
        int monthParcentage = date_1_mm + date_2_mm;
        int yearParcentage = date_1_yy + date_2_yy;

        int ddmm = dayParcentage * monthParcentage;
        parcentage = yearParcentage / ddmm;

        return parcentage;
    }
}
