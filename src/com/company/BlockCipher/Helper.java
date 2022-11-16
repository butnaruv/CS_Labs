package com.company.BlockCipher;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    //permute any list according to a rule table
    public static ArrayList<Integer> PermuteList(ArrayList<Integer> binaryList, List<Integer> permuteRule) {
        ArrayList<Integer> PermutedList = new ArrayList<>();
        //iterate the rule table
        for (int i = 0; i < permuteRule.size(); i++) {
            //get index from rule table
            int TableIndex = permuteRule.get(i);
            //get value at the specific index and add this value to permuted list
            int valueSelected = binaryList.get(TableIndex - 1);
            PermutedList.add(valueSelected);
        }
        return PermutedList;
    }
}
