package com.company.UnitTests.ClassicCryptography;

import com.company.ClassicalCiphers.CaesarCipher;

public class TestCaesarClassic {
    static String message = "cryptography!";
    static int key = 4;
    public static void TestCaesarClassic(){
        System.out.println("Encrypted message using Caesar Cipher:  ");
        String cipherText = CaesarCipher.Encrypt(message, key);
        String plainText = CaesarCipher.Decrypt(cipherText, key);
        if(message.equals(plainText)){
            System.out.println("Caesar cipher works fine! :)");
        } else {
            System.out.println("Caesar cipher has some problems... :( ");
        }
    }
}
