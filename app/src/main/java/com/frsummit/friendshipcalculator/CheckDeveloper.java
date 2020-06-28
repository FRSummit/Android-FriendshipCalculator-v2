package com.frsummit.friendshipcalculator;

public class CheckDeveloper {

    public boolean checkDeveloperAndHisWife(String name_1, String name_2) {
        name_1 = name_1.toUpperCase();
        name_2 = name_2.toUpperCase();

//        String parts_1[] = name_1.split(" ");
//        String parts_2[] = name_2.split(" ");
//
//        int largestName = parts_1.length < parts_2.length ? parts_2.length : parts_1.length;
//
//        for(int i=0; i<largestName; i++) {
//            if(parts_1[i] == "FAYAZUR" && parts_2[0] == "HAFSINA") return false;
//            else if(parts_1[i] == "RAHMAN" && parts_2[0] == "") return false;
//            else if(parts_1[i] == "SUMMIT" && parts_2[0] == "") return false;
//            else if(parts_1[i] == "HAFSINA" && parts_2[0] == "") return false;
//            else if(parts_1[i] == "SHEHERIN" && parts_2[0] == "") return false;
//            else if(parts_1[i] == "RIMI" && parts_2[0] == "") return false;
//        }

        if(name_1.equals("FAYAZUR RAHMAN SUMMIT") && name_2.equals("HAFSINA SHEHERIN RIMI)")) {
            return false;
        } else if(name_1.equals("SUMMIT") && name_2.equals("HAFSINA")) {
            return false;
        } else if(name_1.equals("SUMMIT") && name_2.equals("SHEHERIN")) {
            return false;
        } else if(name_1.equals("SUMMIT") && name_2.equals("RIMI")) {
            return false;
        } else if(name_1.equals("HAFSINA SHEHERIN RIMI") && name_2.equals("FAYAZUR RAHMAN SUMMIT")) {
            return false;
        } else if(name_1.equals("HAFSINA") && name_2.equals("SUMMIT")) {
            return false;
        } else if(name_1.equals("SHEHERIN") && name_2.equals("SUMMIT")) {
            return false;
        } else if(name_1.equals("RIMI") && name_2.equals("SUMMIT")) {
            return false;
        }
        else return true;
    }
}
