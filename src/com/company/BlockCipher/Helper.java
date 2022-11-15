package com.company.BlockCipher;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    public static ArrayList<Integer> PermuteList(ArrayList<Integer> binaryList, List<Integer> permuteRule) {
        ArrayList<Integer> PermutedList = new ArrayList<>();

        for (int i = 0; i < permuteRule.size(); i++) {
            //get index from rule table
            int TableIndex = permuteRule.get(i);
            //get value at the specific index
            int valueSelected = binaryList.get(TableIndex - 1);
            PermutedList.add(valueSelected);
        }

        return PermutedList;
    }
}
