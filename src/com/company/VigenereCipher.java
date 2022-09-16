package com.company;

public class VigenereCipher {
    static String ReplaceTheMessageWithTheKey(String s, String key) {
        String newString = "";
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = i - key.length() * j;
            if (len == key.length()) {
                j = j + 1;
            }
            len = i - key.length() * j;
            newString += key.charAt(len);
        }
        System.out.println(newString);
        return newString;
    }

    static String VigenereEncryption(String s, String key) {
        String newMessage = ReplaceTheMessageWithTheKey(s, key);
        String encryptedMessage = "";
        int encryptedindex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                encryptedindex = (CaesarCipher.LowerCharToNumber(s.charAt(i)) + CaesarCipher.LowerCharToNumber(newMessage.charAt(i))) % 26;
                encryptedMessage += CaesarCipher.NumberToLowerChar(encryptedindex);
            } else if (Character.isUpperCase(s.charAt(i))) {
                encryptedindex = (CaesarCipher.UpperCharToNumber(s.charAt(i)) + CaesarCipher.LowerCharToNumber(newMessage.charAt(i))) % 26;
                encryptedMessage += CaesarCipher.NumberToUpperChar(encryptedindex);
            } else {
                encryptedMessage += s.charAt(i);
            }

        }
        return encryptedMessage;
    }

    static String VigenereDecryption(String s, String key) {
        String newMessage = ReplaceTheMessageWithTheKey(s, key);
        String decryptedMessage = "";
        int decryptedindex = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                System.out.println(i + " " + CaesarCipher.LowerCharToNumber(s.charAt(i)));
                System.out.println(i + " " + CaesarCipher.LowerCharToNumber(newMessage.charAt(i)));
                decryptedindex = (CaesarCipher.LowerCharToNumber(s.charAt(i)) - CaesarCipher.LowerCharToNumber(newMessage.charAt(i)) + 26) % 26;
                System.out.println(decryptedindex);
                decryptedMessage += CaesarCipher.NumberToLowerChar(decryptedindex);
            } else if (Character.isUpperCase(s.charAt(i))) {
                System.out.println(i + " " + CaesarCipher.UpperCharToNumber(s.charAt(i)));
                System.out.println(i + " " + CaesarCipher.LowerCharToNumber(newMessage.charAt(i)));
                decryptedindex = (CaesarCipher.UpperCharToNumber(s.charAt(i)) - CaesarCipher.LowerCharToNumber(newMessage.charAt(i)) + 26) % 26;
                System.out.println(decryptedindex);
                decryptedMessage += CaesarCipher.NumberToUpperChar(decryptedindex);
            } else {
                decryptedMessage += s.charAt(i);
            }

        }
        return decryptedMessage;
    }
}
