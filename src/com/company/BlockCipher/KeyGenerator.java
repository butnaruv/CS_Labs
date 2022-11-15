package com.company.BlockCipher;

import com.company.Utils.LettersToBinaryConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeyGenerator {
    private static String key = "12345678";
    private static List<Integer> PC1 = Arrays.asList(
            57, 49, 41, 33, 25, 17, 9, 1, 58, 50,
            42, 34, 26, 18, 10, 2, 59, 51, 43, 35,
            27, 19, 11, 3, 60, 52, 44, 36, 63, 55,
            47, 39, 31, 23, 15, 7, 62, 54, 46, 38,
            30, 22, 14, 6, 61, 53, 45, 37, 29, 21,
            13, 5, 28, 20, 12, 4);
    private static List<Integer> PC2 = Arrays.asList(
            14, 17, 11, 24, 1, 5, 3, 28, 15, 6,
            21, 10, 23, 19, 12, 4, 26, 8, 16, 7,
            27, 20, 13, 2, 41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48, 44, 49, 39, 56,
            34, 53, 46, 42, 50, 36, 29, 32);

    public static ArrayList<ArrayList<Integer>> GenerateKey() {

        ArrayList<Integer> binaryKey = LettersToBinaryConverter.ConvertListToIntegers(key);
        //permutate key according PC1 rule table
        binaryKey = Helper.PermuteList(binaryKey, PC1);
        //split key in two equal parts
        ArrayList<Integer> C0 = new ArrayList<>(binaryKey.subList(0, 28));
        ArrayList<Integer> D0 = new ArrayList<>(binaryKey.subList(28, 56));
        ArrayList<ArrayList<Integer>> listOfSubKeys = new ArrayList<>();
        //shift key to the left 16 times.
        for (int i = 0; i < 16; i++) {
            C0 = ShiftKey(C0, i);
            D0 = ShiftKey(D0, i);
            ArrayList<Integer> ShiftedKey = new ArrayList<>();
            //Add C and D to Shifted Key
            ShiftedKey.addAll(C0);
            ShiftedKey.addAll(D0);
            //permute shifted key according PC2 rule table
            binaryKey = Helper.PermuteList(ShiftedKey, PC2);
            listOfSubKeys.add(binaryKey);
        }
        return listOfSubKeys;
    }

    private static ArrayList<Integer> ShiftKey(ArrayList<Integer> list, int iterationNumber) {
        int nrOfShifts;
        if (iterationNumber == 1 || iterationNumber == 2 || iterationNumber == 9 || iterationNumber == 16) {
            nrOfShifts = 1;
        } else {
            nrOfShifts = 2;
        }
        for (int i = 0; i < nrOfShifts; i++) {
            list = ShiftLogic(list);
        }
        return list;
    }

    private static ArrayList<Integer> ShiftLogic(ArrayList<Integer> list) {
        ArrayList<Integer> ShiftedList = new ArrayList<>();
        Integer firstElement = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            Integer elementToBeShifted = list.get(i);
            ShiftedList.add(i - 1, elementToBeShifted);
        }
        ShiftedList.add(firstElement);
        return ShiftedList;
    }
}
