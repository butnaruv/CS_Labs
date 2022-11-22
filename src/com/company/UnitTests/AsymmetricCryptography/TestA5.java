package com.company.UnitTests.AsymmetricCryptography;

import com.company.SymmetricCiphers.StreamCipher.A5Cipher;

public class TestA5 {
        public static void TestA5Cipher() {
            String message = "good";
            String cipherText = A5Cipher.Encrypt(message);
            String plainText = A5Cipher.Decrypt(cipherText);
            if(message.equals(plainText)){
                System.out.println("A5/1 cipher works fine! :)");
            } else {
                System.out.println("A5/1 cipher has some problems... :( ");
            }        }
}
