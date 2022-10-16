package com.company;

public class CaesarCipher extends LettersManipulation{


    static String Encrypt(String message, int key) {
        StringBuilder encryptedMessage = new StringBuilder();
        int indexOfEncryptedLetter;
        for (char character : message.toCharArray()) {
            if (Character.isLowerCase(character)) {
                indexOfEncryptedLetter = (LowerCharToNumber(character) + key) % 26;
                encryptedMessage.append(NumberToLowerChar(indexOfEncryptedLetter));
            } else if (Character.isUpperCase(character)) {
                indexOfEncryptedLetter = (UpperCharToNumber(character) + key) % 26;
                encryptedMessage.append(NumberToUpperChar(indexOfEncryptedLetter));
            } else {
                encryptedMessage.append(character);
            }
        }
        return encryptedMessage.toString();
    }

    static String Decrypt(String message, int key) {
        StringBuilder decryptedMessage = new StringBuilder();
        int indexOfDecryptedLetter;
        for (char character : message.toCharArray()) {
            if (Character.isLowerCase(character)) {
                indexOfDecryptedLetter = (LowerCharToNumber(character) - key + 26) % 26;
                decryptedMessage.append(NumberToLowerChar(indexOfDecryptedLetter));
            } else if (Character.isUpperCase(character)) {
                indexOfDecryptedLetter = (UpperCharToNumber(character) - key + 26) % 26;
                decryptedMessage.append(NumberToUpperChar(indexOfDecryptedLetter));
            } else {
                decryptedMessage.append(character);
            }
        }
        return decryptedMessage.toString();
    }

}
