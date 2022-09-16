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
        // System.out.println(CaesarCipherWithPermutation.StartTheNewAlphabetList(key,CaesarCipherWithPermutation.AddLettersInList()));
        ///////Second example
//        String key = "dina";
//        String newMessage1 = CaesarCipherWithPermutation.EncryptionWithPermutation("eu AM dispozitie", key);
//        System.out.println(newMessage1);
//        String newMessage2 = CaesarCipherWithPermutation.DecryptionWithPermutation("Lddddegn", key);
//        System.out.println(newMessage2);
        ////Third example
        System.out.println(VigenereCipher.VigenereEncryption("a mea", "sn"));
        System.out.println(VigenereCipher.VigenereDecryption("Zrq", "sn"));
    }

}







