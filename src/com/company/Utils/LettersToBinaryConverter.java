package com.company.Utils;

import java.util.ArrayList;
import java.util.Arrays;

public class LettersToBinaryConverter {
    public static String LettersConverter(String message) {
        //ArrayList<Integer> binaryMessage = new ArrayList<>();
        String binaryMessage = "";
        for (Character character : message.toCharArray()) {
            var asciiCode = (int) character;
            if (asciiCode == 32) continue;
            // System.out.println(asciiCode);
            var a = DecimalToBinaryConverter(asciiCode);
            //binaryMessage.add(Integer.valueOf(DecimalToBinaryConverter(asciiCode)));
            binaryMessage += DecimalToBinaryConverter(asciiCode);

        }
        return binaryMessage;
    }

    public static String DecimalToBinaryConverter(int asciiCode) {
        String binary = "";
        int aasciicode = asciiCode;
        //System.out.println("ascii: " + asciiCode);
        while (asciiCode != 0) {
            int remainder = asciiCode % 2;
            asciiCode = asciiCode / 2;
            binary = binary + remainder;
        }
        if (aasciicode < 64) {
            //System.out.println("The if is entered: ");
            binary = "00" + InverseString(binary);
        } else if (aasciicode < 128 && aasciicode > 64) {
            binary = "0" + InverseString(binary);
        } else binary = InverseString(binary);
        //System.out.println("In binary: " + binary);
        return binary;
    }

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

