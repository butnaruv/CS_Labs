package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
//        String s = "Ana are mere";
//        int key = 8;
//        String s1 = CaesarCipher.EncodingCaesarCipher(s, key);
//        System.out.println(s1);
//        String s2 = CaesarCipher.DecodingCaesarCipher(s1, key);
//        System.out.println(s2);
        // System.out.println(CaesarCipherWithPermutation.AddLettersInList());
        String key = "dina";
        // System.out.println(CaesarCipherWithPermutation.StartTheNewAlphabetList(key,CaesarCipherWithPermutation.AddLettersInList()));
        String newMessage1 = CaesarCipherWithPermutation.EncryptionWithPermutation("eu AM dispozitie", key);
        System.out.println(newMessage1);
        String newMessage2 = CaesarCipherWithPermutation.DecryptionWithPermutation("Lddddegn", key);
        System.out.println(newMessage2);
    }

}







