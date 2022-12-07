package com.company.AsymmetricCipher;

import java.math.BigInteger;
import java.util.ArrayList;

import static com.company.AsymmetricCipher.KeyGenerator.*;
import static com.company.AsymmetricCipher.KeyGenerator.DecryptKey;

public class RSA {
    private static int limitOfPrimes = 400;
    private static ArrayList<Integer> primeNumbers = SieveOfEratosthenes(limitOfPrimes);

    private static int p = RandomGenerator(primeNumbers);
    private static int q = RandomGenerator(primeNumbers);
    private static int NValue;
    private static int FiFunction;
    private static int encryptKey;
    private static int decryptKey;

    public static String Encrypt(String message) {

        //variable for decimal value of plain text (BigInteger)
        BigInteger plainTextDecimalCharacter;
        //variable for decimal value of cipher text (BigInteger)
        BigInteger cipherTextDecimalCharacter;
        // variable for cipher text (String)
        String cipherText = "";
        for (char character : message.toCharArray()) {
            plainTextDecimalCharacter = new BigInteger(String.valueOf((int) character));
            //apply formula for converting decimal values
            cipherTextDecimalCharacter = (plainTextDecimalCharacter.pow(encryptKey)).mod(BigInteger.valueOf(NValue));
            //concatenate letters
            cipherText += (char) (cipherTextDecimalCharacter.intValue());
        }
//        System.out.println("Cipher text: " + cipherText);
        return cipherText;
    }

    public static String Decrypt(String encryptedMessage) {

        //variable for decimal value of cipher text (BigInteger)
        BigInteger cipherTextDecimalCharacter;
        //variable for decimal value of plain text (BigInteger)
        BigInteger plainTextDecimalCharacter;
        //variable for plain text (String)
        String plainText = "";
        for (char character : encryptedMessage.toCharArray()) {
            cipherTextDecimalCharacter = new BigInteger(String.valueOf((int) character));
            //apply formula for converting decimal values
            plainTextDecimalCharacter = (cipherTextDecimalCharacter.pow(decryptKey)).mod(BigInteger.valueOf(NValue));
            plainText += (char) (plainTextDecimalCharacter.intValue());
        }
//        System.out.println("Plain text: " + plainText);
        return plainText;
    }

    public static void ComputeKeys() {
        while (p == q) {
            q = RandomGenerator(primeNumbers);
        }
        NValue = p * q;
        FiFunction = (p - 1) * (q - 1);
        encryptKey = EncryptKey(NValue, FiFunction, p, q);
        decryptKey = DecryptKey(encryptKey, FiFunction);

    }

}
