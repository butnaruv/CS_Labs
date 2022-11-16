package com.company.StreamCipher;

import java.util.*;

import static java.util.Arrays.asList;

public class Register {
    //Initialise the LFSR
    public static ArrayList<Integer> reg1 = new ArrayList<Integer>(Collections.nCopies(19, 0));
    public static ArrayList<Integer> reg2 = new ArrayList<Integer>(Collections.nCopies(22, 0));
    public static ArrayList<Integer> reg3 = new ArrayList<Integer>(Collections.nCopies(23, 0));
    //Set list of feedback bits for each LFSR
    public static ArrayList<Integer> feedbackBitsReg1 = new ArrayList<Integer>(asList(13, 16, 17, 18));
    public static ArrayList<Integer> feedbackBitsReg2 = new ArrayList<Integer>(asList(20, 21));
    public static ArrayList<Integer> feedbackBitsReg3 = new ArrayList<Integer>(asList(20, 21, 22));
    //set clocking bits for each LFSR
    public static int clockingBitReg1 = 8;
    public static int clockingBitReg2 = 10;
    public static int clockingBitReg3 = 10;

    //Perform XOR operation only of feedback bits
    private static Integer FeedbackBitsXored(ArrayList<Integer> register, ArrayList<Integer> feedbackBits) {
        Integer nrOfOnes = 0;
        for (int i = 0; i < feedbackBits.size(); i++) {
            //count the number of ones
            if (register.get(feedbackBits.get(i)) == 1)
                nrOfOnes++;
        }
        //if the number of ones is even, the result for XOR is 0
        // else the result for XOR will be one
        if (nrOfOnes % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    //randomly generate 0's and 1's (used for session key and for frame counter)
    public static ArrayList<Integer> RandomGeneratorOfSignals(int number) {
        ArrayList<Integer> listOfSignals = new ArrayList<Integer>();
        Random ran = new Random();
        for (int i = 0; i < number; i++) {
            int nxt = ran.nextInt(2);
            listOfSignals.add(nxt);
        }
        return listOfSignals;
    }

    //shift registers to the right one position.
    private static ArrayList<Integer> ShiftRegister(ArrayList<Integer> register, Integer newValueToAdd) {
        //change the position of each bit, increasing position number with 1
        for (int i = register.size() - 1; i > 0; i--) {
            register.set(i, register.get(i - 1));
        }
        //add new value in the first position
        register.set(0, newValueToAdd);
        return register;
    }

    //XOR operation between feedback bits and the bit from session key / frame counter
    public static ArrayList<Integer> ListOfSignalsAndFeedbackBitsXored(ArrayList<Integer> register, ArrayList<Integer> feedbackBits, ArrayList<Integer> listOfBits) {
        Integer xoredValue = 0;
        for (int i = 0; i < listOfBits.size(); i++) {
            //if the values are equal, XOR value is 0
            //else the XOR value is 1
            if (FeedbackBitsXored(register, feedbackBits) == listOfBits.get(i)) {
                xoredValue = 0;
            } else {
                xoredValue = 1;
            }
            ShiftRegister(register, xoredValue);
        }
        return register;
    }

    private static Integer ExtractValueOfClockingBits(ArrayList<Integer> reg, Integer clockingBit) {
        return reg.get(clockingBit);
    }

    //compute the majority of registers
    public static Integer ComputeMajority() {
        int countZero = 0;
        int countOne = 0;
        //initialize a list to stock value of clocking bits
        ArrayList<Integer> valueOfClockingBits = new ArrayList<>();
        valueOfClockingBits.add(ExtractValueOfClockingBits(reg1, clockingBitReg1));
        valueOfClockingBits.add(ExtractValueOfClockingBits(reg2, clockingBitReg2));
        valueOfClockingBits.add(ExtractValueOfClockingBits(reg3, clockingBitReg3));
        //iterate the list to count number of 0's and 1's
        for (int i = 0; i < 3; i++) {
            if (valueOfClockingBits.get(i) == 0) countZero++;
            else countOne++;
        }
        if (countOne > countZero) return 1;
        else return 0;
    }

    //Clock the register if the value of the clocking bit and the value of majority are equal
    //Clock --> means to perform xor operation for feedback bits and to shift the register one position in order to add the new computed value
    public static ArrayList<Integer> IsClocked(Integer valueOfMajority, ArrayList<Integer> reg, Integer clockingBit, ArrayList<Integer> feedbackBits) {

        return reg;
    }

    //Compute XOR value of the last digit from all the registers (for generating key stream)
    public static Integer LastBitXored() {
        int nrOfOnes = 0;
        if (reg1.get(18) == 1) nrOfOnes++;
        if (reg2.get(21) == 1) nrOfOnes++;
        if (reg3.get(22) == 1) nrOfOnes++;
        if (nrOfOnes % 2 == 0) return 0;
        else return 1;
    }
}
