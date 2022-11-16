package com.company.BlockCipher;

import com.company.Utils.BinaryToLettersConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageManipulator {
    //E-bit selection table
    private static List<Integer> EP = Arrays.asList(
            32, 1, 2, 3, 4, 5, 4, 5, 6, 7,
            8, 9, 8, 9, 10, 11, 12, 13, 12, 13,
            14, 15, 16, 17, 16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1);

    //SBox table
    private static Integer[][][] SBox
            = {
            {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}},

            {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}},

            {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}},

            {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}},

            {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}},

            {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}},

            {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}},

            {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}};

    //P permutation table
    private static List<Integer> P = Arrays.asList(
            16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23,
            26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27,
            3, 9, 19, 13, 30, 6, 22, 11, 4, 25);

    public static ArrayList<Integer> Round(ArrayList<Integer> binaryMessage, ArrayList<Integer> subKey, int iteration) {
        //Divide message in two equal parts
        ArrayList<Integer> left = new ArrayList<>(binaryMessage.subList(0, binaryMessage.size() / 2));
        ArrayList<Integer> right = new ArrayList<>(binaryMessage.subList(binaryMessage.size() / 2, binaryMessage.size()));
        // * newLeft --> Left_n  * newRight --> Right_n
        // *left --> Left_(n-1)  * right --> Right_(n-1)
        ArrayList<Integer> newLeft = right;
        //compute list of S(B) --> result of FFunction.
        ArrayList<Integer> BList = FFunction(right, subKey);
        ArrayList<Integer> newRight = new ArrayList<>();
        //xor list of results of FFunction and left part
        for (int i = 0; i < left.size(); i++) {
            newRight.add(Xor(left.get(i), BList.get(i)));
        }
        //initialize a list to store left and right values updated.
        ArrayList<Integer> newLeftAndRight = new ArrayList<>();
        //for the last iteration, concatenate Right, than Left
        //otherwise, concatenate Left than Right
        if (iteration == 15) {
            newLeftAndRight.addAll(newRight);
            newLeftAndRight.addAll(newLeft);
        } else {
            newLeftAndRight.addAll(newLeft);
            newLeftAndRight.addAll(newRight);
        }
        return newLeftAndRight;
    }

    private static ArrayList<Integer> FFunction(ArrayList<Integer> right, ArrayList<Integer> subKey) {
        ArrayList<Integer> rightXorKey = new ArrayList<>();
        //Expand right part from 32 bits to 48 bits using e-bit selection table
        right = Helper.PermuteList(right, EP);
        //xor subkey and permuted right part
        for (int i = 0; i < right.size(); i++) {
            Integer valueToAdd = Xor(right.get(i), subKey.get(i));
            rightXorKey.add(valueToAdd);
        }
        //compute the coordinates of the element to take from the s-box
        int rowNumber, columnNumber, element;
        ArrayList<Integer> BList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            var bValue = ExtractBValue(rightXorKey);
            rowNumber = ComputeRow(bValue);
            columnNumber = ComputeColumn(bValue);
            element = SBox[i][rowNumber][columnNumber];
            //convert the element in 4-bit binary format and add to list
            BList.addAll(ConvertDecimalTo4BitsBinary(element));
        }
        //Permute the binary output of the s-function
        BList = Helper.PermuteList(BList, P);
        return BList;
    }

    //extract B Value from the result obtained after xoring E(R) and Key
    //B value represent a 6 bit sequence of 0's and 1's
    private static ArrayList<Integer> ExtractBValue(ArrayList<Integer> list) {
        ArrayList<Integer> bValue = new ArrayList<>();
        //iterate up to 6 to get a 6-bit sequence
        for (int i = 0; i < 6; i++) {
            bValue.add(list.get(0));
            //remove from the input list the first element.
            // The size of list will decrease with 1 at each iteration.
            list.remove(0);
        }
        return bValue;
    }

    //compute row from s-box
    private static Integer ComputeRow(ArrayList<Integer> bValue) {
        ArrayList<Integer> rowNumber = new ArrayList<>();
        //take 2 bit from the list, the first and the last ones
        rowNumber.add(bValue.get(0));
        rowNumber.add(bValue.get(5));
        //convert the 2-bit result in a decimal value
        return BinaryToLettersConverter.ConvertBinaryToDecimal(rowNumber);
    }

    //compute column from s-box
    private static Integer ComputeColumn(ArrayList<Integer> bValue) {
        ArrayList<Integer> columnNumber = new ArrayList<>();
        //take 4 bit from the list, starting with the one at the position 1
        for (int i = 1; i <= 4; i++) {
            columnNumber.add(bValue.get(i));
        }
        //convert the 4-bit result in a decimal value
        return BinaryToLettersConverter.ConvertBinaryToDecimal(columnNumber);
    }

    //transform number in binary of 4 bits
    private static ArrayList<Integer> ConvertDecimalTo4BitsBinary(int asciiCode) {
        ArrayList<Integer> binary = new ArrayList<>();
        //compute each bit using modulo.
        // * remainder --> bit to be added to the list
        while (asciiCode != 0) {
            int remainder = asciiCode % 2;
            asciiCode = asciiCode / 2;
            binary.add(remainder);
        }
        //assure that the conversed decimal has 4 digits in binary.
        while (binary.size() < 4) {
            binary.add(0);
        }
        ArrayList<Integer> binaryReversed = new ArrayList<>();
        //reverse the list
        for (int i = (binary.size() - 1); i >= 0; i--) {
            binaryReversed.add(binary.get(i));
        }
        return binaryReversed;
    }

    //xor function of two values
    private static Integer Xor(Integer firstValue, Integer secondValue) {
        if (firstValue == secondValue) {
            return 0;
        } else {
            return 1;
        }
    }
}
