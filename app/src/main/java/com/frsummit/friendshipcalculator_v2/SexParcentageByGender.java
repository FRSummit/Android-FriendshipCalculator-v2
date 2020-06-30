package com.frsummit.friendshipcalculator_v2;

public class SexParcentageByGender {

    public int getSexParcentage(String sex_1, String sex_2) {
        int parcentage = 0;
        if(sex_1 == "Male" &&  sex_2 == "Male") parcentage = 85;
        else if(sex_1 == "Female" &&  sex_2 == "Female") parcentage = 85;
        else if(sex_1 == "Male" &&  sex_2 == "Female") parcentage = 30;
        else if(sex_1 == "Female" &&  sex_2 == "Male") parcentage = 30;
        else if(sex_1 == "Other" &&  sex_2 == "Other") parcentage = 60;
        else if(sex_1 == "Male" &&  sex_2 == "Other") parcentage = 50;
        else if(sex_1 == "Female" &&  sex_2 == "Other") parcentage = 50;
        else if(sex_1 == "Other" &&  sex_2 == "Male") parcentage = 50;
        else if(sex_1 == "Other" &&  sex_2 == "Female") parcentage = 50;
        return parcentage;
    }
}
