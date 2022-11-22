package com.company.SymmetricCiphers.StreamCipher;

import com.company.Utils.BinaryToLettersConverter;
import com.company.Utils.LettersToBinaryConverter;

import java.util.ArrayList;

public class A5Cipher {
    static ArrayList<Integer> keyStream = new ArrayList<>();

    public static String Encrypt(String message) {
        //convert the string message into arrayList of 0's and 1's
        ArrayList<Integer> messageConvertedInBinary = LettersToBinaryConverter.ConvertListToIntegers(message);

        //randomly generate the session key
        ArrayList<Integer> sessionkey = Register.RandomGeneratorOfSignals(64);

        //xor feedbackbits and session key
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg1, Register.feedbackBitsReg1, sessionkey);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg2, Register.feedbackBitsReg2, sessionkey);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg3, Register.feedbackBitsReg3, sessionkey);

        //randomly generate frame counter
        ArrayList<Integer> frameCounter = Register.RandomGeneratorOfSignals(22);

        //xor feedback bits and frame counter
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg1, Register.feedbackBitsReg1, frameCounter);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg2, Register.feedbackBitsReg2, frameCounter);
        Register.ListOfSignalsAndFeedbackBitsXored(Register.reg3, Register.feedbackBitsReg3, frameCounter);

        //compute majority 100 times and clock the registers if needed
        for (int i = 0; i < 100; i++) {
            //value to stock majority
            Integer valueOfMajority = Register.ComputeMajority();
            Register.IsClocked(valueOfMajority, Register.reg1, Register.clockingBitReg1, Register.feedbackBitsReg1);
            Register.IsClocked(valueOfMajority, Register.reg2, Register.clockingBitReg2, Register.feedbackBitsReg2);
            Register.IsClocked(valueOfMajority, Register.reg3, Register.clockingBitReg3, Register.feedbackBitsReg3);
        }

        //generate key stream
        for (int i = 0; i < 228; i++) {
            Integer generateKeyStream = Register.LastBitXored();
            keyStream.add(generateKeyStream);
            //value to stock majority
            Integer valueOfMajority = Register.ComputeMajority();
            Register.IsClocked(valueOfMajority, Register.reg1, Register.clockingBitReg1, Register.feedbackBitsReg1);
            Register.IsClocked(valueOfMajority, Register.reg2, Register.clockingBitReg2, Register.feedbackBitsReg2);
            Register.IsClocked(valueOfMajority, Register.reg3, Register.clockingBitReg3, Register.feedbackBitsReg3);
        }
        //XOR binary message and generated key stream
        ArrayList<Integer> encryptedBinaryMessage = new ArrayList<>();
        for (int i = 0; i < messageConvertedInBinary.size(); i++) {
            if (messageConvertedInBinary.get(i) == keyStream.get(i)) encryptedBinaryMessage.add(0);
            else encryptedBinaryMessage.add(1);
        }
        //transform the numbers into letters
        return BinaryToLettersConverter.ConvertToCharacters(encryptedBinaryMessage);
    }

    public static String Decrypt(String encryptedMessage) {
        //convert the string message into arrayList of 0's and 1's
        ArrayList<Integer> encryptedBinaryMessage = LettersToBinaryConverter.ConvertListToIntegers(encryptedMessage);

        //XOR encrypted binary message and key stream
        ArrayList<Integer> decryptedBinaryMessage = new ArrayList<>();
        for (int i = 0; i < encryptedBinaryMessage.size(); i++) {
            if (encryptedBinaryMessage.get(i) == keyStream.get(i)) decryptedBinaryMessage.add(0);
            else decryptedBinaryMessage.add(1);
        }

        //transform the numbers into letters
        return BinaryToLettersConverter.ConvertToCharacters(decryptedBinaryMessage);
    }

}
