package com.company;

public class VigenereCipher extends LettersManipulation {
    static String ReplaceTheMessageWithTheKey(String message, String key) {
        StringBuilder concatenatedKey = new StringBuilder();
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            if (j == key.length()) {
                j = 0;
            }
            if (Character.isUpperCase(message.charAt(i)) || Character.isLowerCase(message.charAt(i))) {
                concatenatedKey.append(key.charAt(j));
                j += 1;
            } else {
                concatenatedKey.append(" ");
            }

        }
        return concatenatedKey.toString();
    }

    static String Encrypt(String message, String key) {
        String newMessage = ReplaceTheMessageWithTheKey(message, key);
        StringBuilder encryptedMessage = new StringBuilder();
        int indexOfLetter;
        for (int i = 0; i < message.length(); i++) {
            if (Character.isLowerCase(message.charAt(i))) {
                indexOfLetter = (LowerCharToNumber(message.charAt(i)) + LowerCharToNumber(newMessage.charAt(i))) % 26;
                encryptedMessage.append(NumberToLowerChar(indexOfLetter));
            } else if (Character.isUpperCase(message.charAt(i))) {
                indexOfLetter = (UpperCharToNumber(message.charAt(i)) + LowerCharToNumber(newMessage.charAt(i))) % 26;
                encryptedMessage.append(NumberToUpperChar(indexOfLetter));
            } else {
                encryptedMessage.append(message.charAt(i));
            }

        }
        return encryptedMessage.toString();
    }

    static String Decrypt(String message, String key) {
        String newMessage = ReplaceTheMessageWithTheKey(message, key);
        StringBuilder decryptedMessage = new StringBuilder();
        int indexOfLetter;
        for (int i = 0; i < message.length(); i++) {
            if (Character.isLowerCase(message.charAt(i))) {
                indexOfLetter = (LowerCharToNumber(message.charAt(i)) - LowerCharToNumber(newMessage.charAt(i)) + 26) % 26;
                decryptedMessage.append(NumberToLowerChar(indexOfLetter));
            } else if (Character.isUpperCase(message.charAt(i))) {
                indexOfLetter = (UpperCharToNumber(message.charAt(i)) - LowerCharToNumber(newMessage.charAt(i)) + 26) % 26;
                decryptedMessage.append(NumberToUpperChar(indexOfLetter));
            } else {
                decryptedMessage.append(message.charAt(i));
            }

        }
        return decryptedMessage.toString();
    }
}
