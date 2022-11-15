package com.company.StreamCipher;

import com.company.Utils.BinaryToLettersConverter;
import com.company.Utils.LettersToBinaryConverter;

import java.util.ArrayList;

public class PerformOperations {
    public static void performOperations() {
        System.out.println("1 : " + Register.reg1);
        System.out.println("2 : " + Register.reg2);
        System.out.println("3 : " + Register.reg3);
        ArrayList<Integer> sessionkey = Register.RandomGeneratorOfSignals(64);
        System.out.println("Session key: " + sessionkey);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg1, Register.feedbackBitsReg1, sessionkey);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg2, Register.feedbackBitsReg2, sessionkey);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg3, Register.feedbackBitsReg3, sessionkey);
        System.out.println("1 : " + Register.reg1);
        System.out.println("2 : " + Register.reg2);
        System.out.println("3 : " + Register.reg3);



        ArrayList<Integer> frameCounter = Register.RandomGeneratorOfSignals(22);
        System.out.println("Frame counter: " + frameCounter);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg1, Register.feedbackBitsReg1, frameCounter);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg2, Register.feedbackBitsReg2, frameCounter);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg3, Register.feedbackBitsReg3, frameCounter);
        System.out.println("1 : " + Register.reg1);
        System.out.println("2 : " + Register.reg2);
        System.out.println("3 : " + Register.reg3);
        //System.out.println(Register.ComputeMajority());
        for (int i = 0; i < 100; i++) {
            Integer valueOfMajority = Register.ComputeMajority();
            System.out.println("VALUE OF MAJORITY: " + valueOfMajority);
            Register.IsClocked(valueOfMajority, Register.reg1, Register.clockingBitReg1, Register.feedbackBitsReg1);
            System.out.println("1 : " + Register.reg1);
            Register.IsClocked(valueOfMajority, Register.reg2, Register.clockingBitReg2, Register.feedbackBitsReg2);
            System.out.println("2 : " + Register.reg2);
            Register.IsClocked(valueOfMajority, Register.reg3, Register.clockingBitReg3, Register.feedbackBitsReg3);
            System.out.println("3 : " + Register.reg3);
        }
        System.out.println("<<< REGISTER AFTER MAJORITY CHECK >>> ");
        System.out.println("1 : " + Register.reg1);
        System.out.println("2 : " + Register.reg2);
        System.out.println("3 : " + Register.reg3);



        ArrayList<Integer> keyStream = new ArrayList<>();
        for (int i = 0; i < 228; i++) {

            Integer generateKeyStream = Register.LastBitXored();
            keyStream.add(generateKeyStream);
            Integer valueOfMajority = Register.ComputeMajority();
            //System.out.println("VALUE OF MAJORITY: " + valueOfMajority);
            Register.IsClocked(valueOfMajority, Register.reg1, Register.clockingBitReg1, Register.feedbackBitsReg1);
            //System.out.println("1 : " + Register.reg1);
            Register.IsClocked(valueOfMajority, Register.reg2, Register.clockingBitReg2, Register.feedbackBitsReg2);
            //System.out.println("2 : " + Register.reg2);
            Register.IsClocked(valueOfMajority, Register.reg3, Register.clockingBitReg3, Register.feedbackBitsReg3);
            //System.out.println("3 : " + Register.reg3);
        }
        System.out.println("KEY STREAM");
        System.out.println(keyStream);

        //encrypt the message
        String mess = "eu eu eu";
        ArrayList<Integer> messageConvertedInBinary = LettersToBinaryConverter.ConvertListToIntegers(mess);
        System.out.println(messageConvertedInBinary);
        int s;
        if (messageConvertedInBinary.size() > 228) {
            s = 228;
        } else s = messageConvertedInBinary.size();
        ArrayList<Integer> encryptedBinaryMessage = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            if(messageConvertedInBinary.get(i) == keyStream.get(i)) encryptedBinaryMessage.add(0);
            else encryptedBinaryMessage.add(1);
        }
        System.out.println("ENCRYPTED");
        System.out.println(encryptedBinaryMessage);
        //transform the numbers into letters
        System.out.println(BinaryToLettersConverter.PerformConversion(encryptedBinaryMessage));





    }
}
