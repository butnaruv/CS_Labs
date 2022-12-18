package com.csLabs.jwtauthentication.Service.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class LettersToBinaryConverter {
    public static String LettersConverter(String message) {
        String binaryMessage = "";
        for (Character character : message.toCharArray()) {
            var asciiCode = (int) character;
            //if the character is space, ignore it
            if (asciiCode == 32) continue;
            var a = DecimalToBinaryConverter(asciiCode);
            binaryMessage += DecimalToBinaryConverter(asciiCode);
        }
        return binaryMessage;
    }

    public static String DecimalToBinaryConverter(int asciiCode) {
        String binary = "";
        //initialize the value to verify if the binary value of it is of 8 bit length
        int ascii = asciiCode;

        while (asciiCode != 0) {
            int remainder = asciiCode % 2;
            asciiCode = asciiCode / 2;
            binary = binary + remainder;
        }
        //if ascii code is less than 64, that means that the value in binary can be stocked with 6 bits
        //--->thus, two 0's are added
        //else if ascii code is between 64 and 128, it has the value in binary of length 7
        //--->this, one 0 is added
        if (ascii < 64) {
            binary = "00" + InverseString(binary);
        } else if (ascii < 128 && ascii > 64) {
            binary = "0" + InverseString(binary);
        } else binary = InverseString(binary);
        return binary;
    }

    //inverse the string
    static String InverseString(String binaryNumber) {
        String binaryInverted = "";
        for (int i = binaryNumber.length() - 1; i >= 0; i--) {
            binaryInverted = binaryInverted + binaryNumber.charAt(i);
        }
        return binaryInverted;
    }

    public static ArrayList<Integer> ConvertListToIntegers(String message) {
        String binaryMessage = LettersToBinaryConverter.LettersConverter(message);
        ArrayList<String> myList = new ArrayList<>(Arrays.asList(binaryMessage.split("")));
        //System.out.println(myList);
        ArrayList<Integer> myIntegerList = new ArrayList<>();
        for (int i = 0; i < myList.size(); i++) {
            myIntegerList.add(Integer.valueOf(myList.get(i)));
        }
        //System.out.println(myIntegerList);
        return myIntegerList;
    }

}

