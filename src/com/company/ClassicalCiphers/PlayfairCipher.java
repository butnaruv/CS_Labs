package com.company.ClassicalCiphers;

import com.company.Utils.LettersManipulation;

import java.util.*;

public class PlayfairCipher extends LettersManipulation {

    static List<Character> ExcludeLetterJ(String key) {
        List<Character> InitialAlphabet = AddLettersInList();
        List<Character> NewAlphabet = new ArrayList<Character>(StartTheNewAlphabetList(key, InitialAlphabet));
        for (int i = 0; i < NewAlphabet.size(); i++) {
            if (NewAlphabet.get(i).equals('j')) {
                NewAlphabet.remove(i);
            }
        }
        return NewAlphabet;
    }

    static char[][] CipherTable(String key) {
        List<Character> NewAlphabet = new ArrayList<Character>(ExcludeLetterJ(key));
        char[][] table = new char[5][5];
        int alphabetIndex = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                table[i][j] = NewAlphabet.get(alphabetIndex);
                alphabetIndex++;
            }
        }
        return table;
    }

    static HashMap<String, ArrayList<Integer>> TableAsHashMap(String key) {
        HashMap<String, ArrayList<Integer>> CipherTableAsHashmap = new HashMap<String, ArrayList<Integer>>();
        char[][] table = CipherTable(key);
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                CipherTableAsHashmap.put(String.valueOf(table[i][j]), new ArrayList<Integer>());
                CipherTableAsHashmap.get(String.valueOf(table[i][j])).add(Integer.valueOf(i));
                CipherTableAsHashmap.get(String.valueOf(table[i][j])).add(Integer.valueOf(j));
            }
        }
        return CipherTableAsHashmap;
    }

    static String OddNumberOfLetters(String message) {
        if (message.length() % 2 == 0) {
            return message;
        } else {
            return message += "x";
        }
    }

    static String ToAddOrNotToAddX(String message) {
        message = message.toLowerCase();
        message = message.replaceAll("\\s+", "");
        StringBuffer newMessage = new StringBuffer(message);
        for (int i = 1; i < newMessage.length(); i += 2) {
            if (newMessage.charAt(i) == newMessage.charAt(i - 1)) {
                newMessage = newMessage.insert(i, 'x');
            }
        }
        return OddNumberOfLetters(newMessage.toString());
    }

    public static String Encrypt(String message, String key) {
        message = ToAddOrNotToAddX(message);
        HashMap<String, ArrayList<Integer>> Table = TableAsHashMap(key);
        char[][] table = CipherTable(key);
        StringBuilder encryptedMessage = new StringBuilder();
        for (int i = 1; i < message.length(); i += 2) {
            Integer xFirstLetter = Table.get(Character.toString(message.charAt(i - 1))).get(0);
            Integer yFirstLetter = Table.get(Character.toString(message.charAt(i - 1))).get(1);
            Integer xSecondLetter = Table.get(Character.toString(message.charAt(i))).get(0);
            Integer ySecondLetter = Table.get(Character.toString(message.charAt(i))).get(1);
            if (xFirstLetter == xSecondLetter) {
                encryptedMessage.append(table[xFirstLetter][(yFirstLetter + 1) % 5]).append(table[xSecondLetter][(ySecondLetter + 1) % 5]);
            } else if (yFirstLetter == ySecondLetter) {
                encryptedMessage.append(table[(xFirstLetter + 1) % 5][yFirstLetter]).append(table[(xSecondLetter + 1) % 5][ySecondLetter]);
            } else {
                encryptedMessage.append(table[xFirstLetter][ySecondLetter]).append(table[xSecondLetter][yFirstLetter]);
            }
        }
        return encryptedMessage.toString();
    }

    public static String Decrypt(String message, String key) {
        message = ToAddOrNotToAddX(message);
        HashMap<String, ArrayList<Integer>> Table = TableAsHashMap(key);
        char[][] table = CipherTable(key);
        String decryptedMessage = "";
        for (int i = 1; i < message.length(); i += 2) {
            Integer xFirstLetter = Table.get(Character.toString(message.charAt(i - 1))).get(0);
            Integer yFirstLetter = Table.get(Character.toString(message.charAt(i - 1))).get(1);
            Integer xSecondLetter = Table.get(Character.toString(message.charAt(i))).get(0);
            Integer ySecondLetter = Table.get(Character.toString(message.charAt(i))).get(1);
            if (xFirstLetter == xSecondLetter) {
                decryptedMessage += String.valueOf(table[xFirstLetter][(yFirstLetter + 4) % 5]) + String.valueOf(table[xSecondLetter][(ySecondLetter + 4) % 5]);
            } else if (yFirstLetter == ySecondLetter) {
                decryptedMessage += String.valueOf(table[(xFirstLetter + 4) % 5][yFirstLetter]) + String.valueOf(table[(xSecondLetter + 4) % 5][ySecondLetter]);
            } else {
                decryptedMessage += String.valueOf(table[xFirstLetter][ySecondLetter]) + String.valueOf(table[xSecondLetter][yFirstLetter]);
            }
        }
        return decryptedMessage;
    }
}
