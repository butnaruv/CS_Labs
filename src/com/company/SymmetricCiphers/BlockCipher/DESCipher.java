package com.company.SymmetricCiphers.BlockCipher;

import com.company.Utils.BinaryToLettersConverter;
import com.company.Utils.LettersToBinaryConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.SymmetricCiphers.BlockCipher.MessageManipulator.Round;

public class DESCipher {
    //private static String message = "actualaz";
    //rule table to permute initial binary message
    public static List<Integer> IP = Arrays.asList(
            58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44,
            36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22,
            14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57,
            49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35,
            27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13,
            5, 63, 55, 47, 39, 31, 23, 15, 7);

    //rule table to permute updated binary message(used in last step)
    private static List<Integer> IP_1 = Arrays.asList(
            40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47,
            15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22,
            62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36,
            4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11,
            51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58,
            26, 33, 1, 41, 9, 49, 17, 57, 25);

    public static String Encrypt(String message, String key) {
        //convert string message in binary
        ArrayList<Integer> binaryMessage = LettersToBinaryConverter.ConvertListToIntegers(message);
        //permute the binary message according to rules from IP
        binaryMessage = Helper.PermuteList(binaryMessage, IP);
        //generate the list of 16 keys
        ArrayList<ArrayList<Integer>> listOfSubKeys = KeyGenerator.GenerateKey(key);
        ArrayList<Integer> subKey;
        //iterate 16 times, in order to perform round operation 16 times (phase III)
        for (int i = 0; i < 16; i++) {
            subKey = listOfSubKeys.get(i);
            //perform round where:
            // * binaryMessage --> List of 0's and 1's to be divided in Left and Right
            // * subKey --> the key used in FFunction to XOR Right
            // * i --> number of iteration (used for tracking when the last iteration will be performed in order to concatenate Right and Left, not the reverse)
            binaryMessage = Round(binaryMessage, subKey, i);
        }
        //permute the updated binaryMessage according to rule table IP_1
        binaryMessage = Helper.PermuteList(binaryMessage, IP_1);
        //convert binary message into letters.
        return BinaryToLettersConverter.ConvertToCharacters(binaryMessage);
    }

    public static String Decrypt(String encryptedMessage, String key) {
        //convert string message in binary
        ArrayList<Integer> binaryEncryptedMessage = LettersToBinaryConverter.ConvertListToIntegers(encryptedMessage);
        //permute the binary message according to rules from IP
        binaryEncryptedMessage = Helper.PermuteList(binaryEncryptedMessage, IP);
        //generate the list of 16 keys
        ArrayList<ArrayList<Integer>> listOfSubKeys = KeyGenerator.GenerateKey(key);
        ArrayList<Integer> subKey;
        int iteration = 0;
        //iterate 16 times, in order to perform round operation 16 times (phase III)
        for (int i = 15; i >= 0; i--) {
            subKey = listOfSubKeys.get(i);
            binaryEncryptedMessage = Round(binaryEncryptedMessage, subKey, iteration);
            iteration++;
        }
        //permute the updated binaryMessage according to rule table IP_1
        binaryEncryptedMessage = Helper.PermuteList(binaryEncryptedMessage, IP_1);
        //convert binary message into letters.
        return BinaryToLettersConverter.ConvertToCharacters(binaryEncryptedMessage);
    }
}
