package com.company.UnitTests.SymmetricCryptography;

import com.company.SymmetricCiphers.BlockCipher.DESCipher;

public class TestDES {
    private static String message = "creative";
    private static String key = "12345678";
    public static void TestDesCipher(){
        String cipherText = DESCipher.Encrypt(message, key);
        String plainText = DESCipher.Decrypt(cipherText, key);
        if(message.equals(plainText)){
            System.out.println("DES cipher works fine! :)");
        } else {
            System.out.println("DES cipher has some problems... :( ");
        }

    }
}
