package com.company.Utils;

import java.util.ArrayList;

public class BinaryToLettersConverter {

    public static String PerformConversion(ArrayList<Integer> encryptedMessage) {
        ArrayList<Integer> binaryLetter = new ArrayList<>();
        String encryptedMessageWithLetters = "";
        for (int i = 0; i < (encryptedMessage.size()) ; i += 8) {
            binaryLetter.clear();
            for (int j = 0; j < 8; j++) {
//                System.out.println("i: " + i + " j: " + j );
//                System.out.println(encryptedMessage.get(i + j));
                binaryLetter.add(encryptedMessage.get(i + j));
            }
            encryptedMessageWithLetters += (char)ConvertBinaryToDecimal(binaryLetter);

        }
        return encryptedMessageWithLetters;
    }
    public static int ConvertBinaryToDecimal(ArrayList<Integer> binaryMessage) {
        int asciiCode = 0;
        for (int i = 0; i < binaryMessage.size(); i++) {
            if (binaryMessage.get(i) == 1) {
                //putem modifica 7-i cu binaryMessage.size - (i + 1) ca sa fie generica
                //int compute = (int) Math.pow(2, (7 - i));
                int compute = (int) Math.pow(2, (binaryMessage.size() - (i + 1)));
                asciiCode = asciiCode + compute;
            }
        }
        //System.out.println(asciiCode);
        return asciiCode;
    }
}
