package com.company.UnitTests.ClassicCryptography;

import com.company.ClassicalCiphers.CaesarCipherWithPermutation;

public class TestCaesarPermutation {
    private static String message = "cryptography";
    private static String keyWord = "alibaba";
    private static int key = 2;
    public static void TestCaesarPermutation(){
        String cipherText = CaesarCipherWithPermutation.Encrypt(message, keyWord, key);
        String plainText = CaesarCipherWithPermutation.Decrypt(cipherText, keyWord, key);
        if(message.equals(plainText)){
            System.out.println("Caesar cipher with permutation works fine! :)");
        } else {
            System.out.println("Caesar cipher with permutation has some problems... :( ");
        }
    }

}
