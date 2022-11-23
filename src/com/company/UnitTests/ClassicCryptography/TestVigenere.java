package com.company.UnitTests.ClassicCryptography;

import com.company.ClassicalCiphers.PlayfairCipher;

public class TestVigenere {
    private static String message = "cryptography";
    private static String keyWord = "alibaba";
    public static void TestVigenere(){
        String cipherText = PlayfairCipher.Encrypt(message, keyWord);
        String plainText = PlayfairCipher.Decrypt(cipherText, keyWord);
        if(message.equals(plainText)){
            System.out.println("Vigenere cipher works fine! :)");
        } else {
            System.out.println("Vigenere cipher has some problems... :( ");
        }
    }

}
