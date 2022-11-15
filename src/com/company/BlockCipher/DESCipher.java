package com.company.BlockCipher;

import com.company.Utils.BinaryToLettersConverter;
import com.company.Utils.LettersToBinaryConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.company.BlockCipher.MessageManipulator.Round;

public class DESCipher {
    private static String message = "actualaz";
    //private static String secondMessage ="®?¼MFC¤{";
    private static List<Integer> IP = Arrays.asList(
            58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44,
            36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22,
            14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57,
            49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35,
            27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13,
            5, 63, 55, 47, 39, 31, 23, 15, 7);

    private static List<Integer> IP_1 = Arrays.asList(40, 8,  48, 16, 56, 24, 64, 32, 39, 7,  47,
            15, 55, 23, 63, 31, 38, 6,  46, 14, 54, 22,
            62, 30, 37, 5,  45, 13, 53, 21, 61, 29, 36,
            4,  44, 12, 52, 20, 60, 28, 35, 3,  43, 11,
            51, 19, 59, 27, 34, 2,  42, 10, 50, 18, 58,
            26, 33, 1,  41, 9,  49, 17, 57, 25);
    public static String Encrypt() {
        //convert string message in binary
        ArrayList<Integer> binaryMessage = LettersToBinaryConverter.ConvertListToIntegers(message);
//        System.out.println("The message in binary: " + binaryMessage);
        binaryMessage = Helper.PermuteList(binaryMessage, IP);
//        System.out.println("Binary Message after permutation: " + binaryMessage);
        ArrayList<ArrayList<Integer>> listOfSubKeys = KeyGenerator.GenerateKey();
        ArrayList<Integer> subKey;
//        System.out.println(Round(binaryMessage, subKey));
        for(int i = 0; i < 16; i++){
            subKey = listOfSubKeys.get(i);
            binaryMessage = Round(binaryMessage,subKey);
        }
        binaryMessage = Helper.PermuteList(binaryMessage, IP_1);
        System.out.println(BinaryToLettersConverter.PerformConversion(binaryMessage));
        return BinaryToLettersConverter.PerformConversion(binaryMessage);
    }

    public static void Decrypt(String secondMessage){
        ArrayList<Integer> binaryencMessage = LettersToBinaryConverter.ConvertListToIntegers(secondMessage);
        binaryencMessage = Helper.PermuteList(binaryencMessage, IP);
//        System.out.println("Binary Message after permutation: " + binaryMessage);
        ArrayList<ArrayList<Integer>> listOfSubKeys = KeyGenerator.GenerateKey();
        ArrayList<Integer> subKey;
//        System.out.println(Round(binaryMessage, subKey));
        for(int i = 15; i >= 0; i--){
            subKey = listOfSubKeys.get(i);
            binaryencMessage = Round(binaryencMessage,subKey);
        }
        binaryencMessage = Helper.PermuteList(binaryencMessage, IP_1);
        System.out.println(BinaryToLettersConverter.PerformConversion(binaryencMessage));
    }
}
