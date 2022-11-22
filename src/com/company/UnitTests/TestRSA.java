package com.company.UnitTests;

import com.company.AsymmetricCipher.RSA;

public class TestRSA {

    public static String message = "cryptography!";
    public static void TestRSA(){

        RSA.ComputeKeys();
        String encMessage = RSA.Encrypt(message);
        RSA.Decrypt(encMessage);
    }
}
