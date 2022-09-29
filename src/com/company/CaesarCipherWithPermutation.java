package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class CaesarCipherWithPermutation extends LettersManipulation {


    //function to obtain the index of a given letter from the list
    static Integer GetIndex(Character character, List<Character> list) {
        int index = 0;
        for (int i = 0; i < 26; i++) {
            if (character == list.get(i)) {
                index = i;
            }
        }
        return index;
    }

    static String Encrypt(String message, String keyWord, int key) {
        List<Character> InitialAlphabet = AddLettersInList();
        List<Character> list = new ArrayList<Character>(StartTheNewAlphabetList(keyWord, InitialAlphabet));
//        System.out.println(InitialAlphabet);
//        System.out.println(list);
        StringBuilder encryptedMessage = new StringBuilder();
        for (char character : message.toCharArray()) {
            int index = 0;
            if (Character.isUpperCase(character)) {
                character = Character.toLowerCase(character);
                index = GetIndex(character, list);
                encryptedMessage.append(Character.toUpperCase(list.get(index + key)));
            } else if (Character.isLowerCase(character)) {
                index = GetIndex(character, list);
                encryptedMessage.append(list.get(index + key));
            } else {
                encryptedMessage.append(character);
            }
        }
        return encryptedMessage.toString();
    }

    static String Decrypt(String message, String keyWord, int key) {
        List<Character> InitialAlphabet = AddLettersInList();
        List<Character> list = new ArrayList<Character>(StartTheNewAlphabetList(keyWord, InitialAlphabet));
//        System.out.println(InitialAlphabet);
//        System.out.println(list);
        StringBuilder decryptedMessage = new StringBuilder();
        for (char character : message.toCharArray()) {
            int index = 0;
            if (Character.isUpperCase(character)) {
                character = Character.toLowerCase(character);
                index = GetIndex(character, list);
                decryptedMessage.append(Character.toUpperCase(list.get((index - key + 26) % 26)));
            } else if (Character.isLowerCase(character)) {
                index = GetIndex(character, list);
                decryptedMessage.append(list.get((index - key + 26) % 26));
            } else {
                decryptedMessage.append(character);
            }
        }
        return decryptedMessage.toString();
    }
}

