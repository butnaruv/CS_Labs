package com.company.UnitTests;

import com.company.AsymmetricCipher.RSA;

import java.util.ArrayList;

import static com.company.AsymmetricCipher.KeyGenerator.*;
import static com.company.AsymmetricCipher.KeyGenerator.DecryptKey;

public class TestRSA {
    private static int limitOfPrimes = 40;
    private static ArrayList<Integer> primeNumbers = SieveOfEratosthenes(limitOfPrimes);

    private static int p = RandomGenerator(primeNumbers);
    private static int q = RandomGenerator(primeNumbers);


    public static String message = "cryptography!";
    public static void TestRSA(){
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        while(p == q){
            q = RandomGenerator(primeNumbers);
            System.out.println("New value of q: " + q);
        }
        int NValue = p * q;
        int FiFunction = (p - 1) * (q - 1);
        int encryptKey = EncryptKey(NValue, FiFunction, p, q);
        int decryptKey = DecryptKey(encryptKey, FiFunction);
        String encMessage = RSA.Encrypt(message, NValue, FiFunction, encryptKey);
        RSA.Decrypt(encMessage, NValue, decryptKey);
    }
}
