package com.company.AsymmetricCipher;

import java.math.BigInteger;

public class RSA {

    // mai intai chemam metoda key generator in unit test, apoi lucram cu valorile pe care le primim
    public static String Encrypt(String message, int NValue, int FiFunction, int encryptKey) {
        System.out.println("--------ENCRYPT--------");
        System.out.println("Nvalue: " + NValue);
        System.out.println("FiFunction: " + FiFunction);
        System.out.println("Encrypt key: " + encryptKey);
        //convert text to decimal number
        BigInteger bigIntegerDecimalCharacter;
        int integerDecimalCharacter;
        //BigInteger convertedDecimalCharacter;
        String convertedString = "";
        for (char character : message.toCharArray()) {
            bigIntegerDecimalCharacter = new BigInteger (String.valueOf((int) character ));
            System.out.println("decimal character: " + bigIntegerDecimalCharacter);
            var convertedDecimalCharacter = (bigIntegerDecimalCharacter.pow(encryptKey)).mod(BigInteger.valueOf(NValue));
            System.out.println(convertedDecimalCharacter);
            integerDecimalCharacter = convertedDecimalCharacter.intValue();
            convertedString += (char)(integerDecimalCharacter );
        }
        System.out.println(convertedString);
        return convertedString;
    }

    public static String Decrypt(String encryptedMessage, int NValue, int decryptKey) {
        System.out.println("--------DECRYPT--------");
        System.out.println("Nvalue: " + NValue);
        //System.out.println("FiFunction: " + FiFunction);
        System.out.println("Decrypt key: " + decryptKey);
        BigInteger bigIntegerDecimalCharacter;
        int integerDecimalCharacter;
        //BigInteger convertedDecimalCharacter;
        String convertedString = "";
        for (char character : encryptedMessage.toCharArray()) {
            int decimalCharacter = (int) character ;
            bigIntegerDecimalCharacter = new BigInteger (String.valueOf(decimalCharacter));
            System.out.println("decimal character: " + bigIntegerDecimalCharacter);
            var convertedDecimalCharacter = (bigIntegerDecimalCharacter.pow(decryptKey)).mod(BigInteger.valueOf(NValue));
            System.out.println(convertedDecimalCharacter);
            int differenceDecimalCharacterAndN = decimalCharacter/NValue;
            integerDecimalCharacter = convertedDecimalCharacter.intValue();
            convertedString += (char)(integerDecimalCharacter );
        }
        System.out.println(convertedString);
        return convertedString;
    }

}
