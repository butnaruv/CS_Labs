package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class CaesarCipherWithPermutation {
    static List<Character> AddLettersInList() {
        List<Character> Alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            Alphabet.add(CaesarCipher.NumberToLowerChar(i));
        }
        return Alphabet;
    }

    static HashSet<Character> StartTheNewAlphabetList(String s, List<Character> Alph) {
        HashSet<Character> NewAlphabet = new LinkedHashSet<Character>();
        for (char character : s.toCharArray()) {
            NewAlphabet.add(character);
        }
        for (int i = 0; i < 26; i++) {
            NewAlphabet.add(Alph.get(i));
        }
        return NewAlphabet;
    }

    //function to obtain the index of a given letter from the list
    static Integer GetIndex(Character ch, List<Character> list) {
        int index = 0;
        for (int i = 0; i < 26; i++) {
            if (ch == list.get(i)) {
                index = i;
            }
        }
        return index;
    }

    static String EncryptionWithPermutation(String message, String key) {
        List<Character> InitialAlphabet = AddLettersInList();
        List<Character> list = new ArrayList<Character>(StartTheNewAlphabetList(key, InitialAlphabet));
//        System.out.println(InitialAlphabet);
//        System.out.println(list);
        String encryptedMessage = "";
        for (char character : message.toCharArray()) {
            int index = 0;
            if (Character.isUpperCase(character)) {
                character = Character.toLowerCase(character);
                index = GetIndex(character, InitialAlphabet);
                encryptedMessage += Character.toUpperCase(list.get(index));
            } else if (Character.isLowerCase(character)) {
                index = GetIndex(character, InitialAlphabet);
                encryptedMessage += list.get(index);
            } else {
                encryptedMessage += character;
            }
        }
        return encryptedMessage;
    }

    static String DecryptionWithPermutation(String message, String key) {
        List<Character> InitialAlphabet = AddLettersInList();
        List<Character> list = new ArrayList<Character>(StartTheNewAlphabetList(key, InitialAlphabet));
        System.out.println(InitialAlphabet);
        System.out.println(list);
        String decryptedMessage = "";
        for (char character : message.toCharArray()) {
            int index = 0;
            if (Character.isUpperCase(character)) {
                character = Character.toLowerCase(character);
                index = GetIndex(character, list);
                decryptedMessage += Character.toUpperCase(InitialAlphabet.get(index));
            } else if (Character.isLowerCase(character)) {
                index = GetIndex(character, list);
                decryptedMessage += InitialAlphabet.get(index);
            } else {
                decryptedMessage += character;
            }
        }
        return decryptedMessage;
    }
}

