package com.csLabs.jwtauthentication.Service.Utils;

import java.util.ArrayList;

public class BinaryToLettersConverter {

    public static String ConvertToCharacters(ArrayList<Integer> encryptedMessage) {
        //initialize list to stock binary values
        ArrayList<Integer> binaryLetter = new ArrayList<>();
        String encryptedMessageWithLetters = "";
        //iterate the message to split it into blocks of 8 bits
        for (int i = 0; i < (encryptedMessage.size()); i += 8) {
            binaryLetter.clear();
            for (int j = 0; j < 8; j++) {
                binaryLetter.add(encryptedMessage.get(i + j));
            }
            encryptedMessageWithLetters += (char) ConvertBinaryToDecimal(binaryLetter);

        }
        return encryptedMessageWithLetters;
    }

    public static int ConvertBinaryToDecimal(ArrayList<Integer> binaryMessage) {
        int asciiCode = 0;
        //iterate the binary list
        for (int i = 0; i < binaryMessage.size(); i++) {
            // when the bit is equl to 1, compute 2 ^ (binaryMessage.size() - (i + 1));
            // ex for 3 bit: * bit 0 --> 2^2
            //               * bit 1 --> 2^1
            //               * bit 2 --> 2^0
            if (binaryMessage.get(i) == 1) {
                int compute = (int) Math.pow(2, (binaryMessage.size() - (i + 1)));
                asciiCode = asciiCode + compute;
            }
        }
        return asciiCode;
    }
}
//putem modifica 7-i cu binaryMessage.size - (i + 1) ca sa fie generica
//int compute = (int) Math.pow(2, (7 - i));