package com.csLabs.jwtauthentication.Service.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public abstract class LettersManipulation {
    public static Integer LowerCharToNumber(char character) {
        int indexOfLetter = (int) character - 97;
        return indexOfLetter;
    }

    public static Integer UpperCharToNumber(char character) {
        int indexOfLetter = (int) character - 65;
        return indexOfLetter;
    }

    public static char NumberToLowerChar(int indexOfLetter) {
        char character = (char) (indexOfLetter + 97);
        return character;
    }

    public static char NumberToUpperChar(int indexOfLetter) {
        char character = (char) (indexOfLetter + 65);
        return character;
    }

    public final static List<Character> AddLettersInList() {
        List<Character> Alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            Alphabet.add(NumberToLowerChar(i));
        }
        return Alphabet;
    }

    public static HashSet<Character> StartTheNewAlphabetList(String keyWord, List<Character> Alphabet) {
        HashSet<Character> NewAlphabet = new LinkedHashSet<Character>();
        for (char character : keyWord.toCharArray()) {
            NewAlphabet.add(character);
        }
        for (int i = 0; i < 26; i++) {
            NewAlphabet.add(Alphabet.get(i));
        }
        return NewAlphabet;
    }

}
