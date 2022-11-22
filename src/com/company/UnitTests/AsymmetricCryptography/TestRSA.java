package com.company.UnitTests.AsymmetricCryptography;

import com.company.AsymmetricCipher.RSA;

public class TestRSA {

    public static String message = "cryptography!";
    public static void TestRSA(){

        RSA.ComputeKeys();
        String cipherText = RSA.Encrypt(message);
        String plainText = RSA.Decrypt(cipherText);
        if(message.equals(plainText)){
            System.out.println("RSA cipher works fine! :)");
        } else {
            System.out.println("RSA cipher has some problems... :( ");
        }
    }
}
