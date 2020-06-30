package com.frsummit.friendshipcalculator_v2;

public class NameParcentageByNumber {
    NumberByLetter numberByLetter = new NumberByLetter();

    public int getNameParcentage(String name_1, String name_2) {
        int parcentage = 0;
        int total_1 = getTotalForSingleName(name_1);
        int total_2 = getTotalForSingleName(name_2);

//        System.out.println(total_1);
//        System.out.println(total_2);
//        System.out.println((int)(Math.log10(total_1)+1));
//        System.out.println((int)(Math.log10(total_2)+1));

        parcentage = total_1 + total_2;
//       System.out.println(parcentage);
        parcentage /= parcentageDivition(parcentage);
//       System.out.println("---------->" + parcentage);
        return parcentage;
    }

    public int getTotalForSingleName(String name) {
        int calc = 0;
        char[] ch = name.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            String s = "";
            while (i < ch.length && ch[i] != ' ') {
//                System.out.println(ch[i]);
//                System.out.println(numberByLetter.getNumberByLetter(String.valueOf(ch[i])));
                int ret = numberByLetter.getNumberByLetter(String.valueOf(ch[i]));
                calc += ret;
//                s = s + ch[i];
                i++;
            }
//            if (s.length() > 0)
//                System.out.println(s + "->" + s.length());
        }
        return calc;
    }

    public int parcentageDivition(int parcentage) {
        int digits = (int)(Math.log10(parcentage)+1);
        int dividedBy = 0;
        switch (digits) {
            case 1:
                dividedBy = 10;
                break;
            case 2:
                dividedBy = 10;
                break;
            case 3:
                dividedBy = 10;
                break;
            case 4:
                dividedBy = 100;
                break;
            case 5:
                dividedBy = 1000;
                break;
            case 6:
                dividedBy = 10000;
                break;
            case 7:
                dividedBy = 100000;
                break;
            case 8:
                dividedBy = 1000000;
                break;
            case 9:
                dividedBy = 10000000;
                break;
            case 10:
                dividedBy = 100000000;
                break;
            default:
                break;
        }
        return dividedBy;
    }
}
