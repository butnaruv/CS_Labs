package com.company.UnitTests.ClassicCryptography;

import com.company.ClassicalCiphers.PlayfairCipher;

public class TestPlayfair {
    private static String message = "cryptography";
    private static String keyWord = "alibaba";

    public static void testPlayfair(){
        String cipherText = PlayfairCipher.Encrypt(message, keyWord);
        String plainText = PlayfairCipher.Decrypt(cipherText, keyWord);
        if(message.equals(plainText)){
            System.out.println("Playfair cipher works fine! :)");
        } else {
            System.out.println("Playfair cipher has some problems... :( ");
        }
    }
}
