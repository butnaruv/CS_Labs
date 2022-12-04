package com.company.UnitTests.DigitalSignature;

import com.company.DigitalSignature.Sha256;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class TestSha256 {
    public static String message = "cryptography!";
    public static void TestSha256() throws IOException, NoSuchAlgorithmException {
        if(Sha256.Main(message)){
            System.out.println("SHA-256 algorithm works fine! Document was successfully signed! :)");
        }else {
            System.out.println("SHA-256 algorithm has some problems... :(");

        }
    }
}
