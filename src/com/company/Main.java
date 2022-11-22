package com.company;

import com.company.UnitTests.AsymmetricCryptography.TestA5;
import com.company.UnitTests.AsymmetricCryptography.TestRSA;
import com.company.UnitTests.ClassicCryptography.TestCaesarClassic;
import com.company.UnitTests.ClassicCryptography.TestPlayfair;
import com.company.UnitTests.ClassicCryptography.TestVigenere;
import com.company.UnitTests.SymmetricCryptography.TestDES;

public class Main {

    public static void main(String[] args) {

        System.out.println("------CLASSICAL CIPHERS------");
        TestCaesarClassic.TestCaesarClassic();
        TestVigenere.testVigenere();
        TestPlayfair.testPlayfair();

        System.out.println();
        System.out.println("------SYMMETRIC CIPHERS------");
        TestA5.TestA5Cipher();
        TestDES.TestDesCipher();

        System.out.println();
        System.out.println("------ASYMMETRIC CIPHERS------");
        TestRSA.TestRSA();

    }
}





