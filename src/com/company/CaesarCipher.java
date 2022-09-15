package com.company;

public class CaesarCipher {
    static Integer LowerCharToNumber(char c) {
        int number = (int) c - 97;
        return number;
    }

    static Integer UpperCharToNumber(char c) {
        int number = (int) c - 65;
        return number;
    }

    static char NumberToLowerChar(int n) {
        char ch = (char) (n + 97);
        return ch;
    }

    static char NumberToUpperChar(int n) {
        char ch = (char) (n + 65);
        return ch;
    }

    static String EncodingCaesarCipher(String s, int key) {
        String newString = "";
        for (char character : s.toCharArray()) {
            if (Character.isLowerCase(character)) {
                int e = (LowerCharToNumber(character) + key) % 26;
                newString += NumberToLowerChar(e);
            } else if (Character.isUpperCase(character)) {
                int e = (UpperCharToNumber(character) + key) % 26;
                newString += NumberToUpperChar(e);
            } else {
                newString += character;
            }
        }
        return newString;
    }

    static String DecodingCaesarCipher(String s, int key) {
        String newString = "";
        for (char character : s.toCharArray()) {
            if (Character.isLowerCase(character)) {
                int e = (LowerCharToNumber(character) - key + 26) % 26;
                newString += NumberToLowerChar(e);
            } else if (Character.isUpperCase(character)) {
                int e = (UpperCharToNumber(character) - key + 26) % 26;
                newString += NumberToUpperChar(e);
            } else {
                newString += character;
            }
        }
        return newString;
    }
}
