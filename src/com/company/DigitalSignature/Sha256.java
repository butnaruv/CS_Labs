package com.company.DigitalSignature;

import com.company.AsymmetricCipher.RSA;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256 {

    public static boolean Main(String message) throws IOException, NoSuchAlgorithmException {
        // 1. get digest
        var digest = GetDigest(message);

        // 2. encrypt hash
        RSA.ComputeKeys();
        var encryptedHash = RSA.Encrypt(digest);
        //remove all spaces in hash
        var encryptedHashWithoutSpaces = encryptedHash.replaceAll("\\s", "");

        // 3. save message and encryptedHash into file (content of file will be fields for verify)
        FileWriter myWriter = new FileWriter("digitalData.txt");
        myWriter.write(message + "\n");
        myWriter.write(encryptedHash);
        myWriter.close();

        // 4. read from file (extract fields (message and encryptedHash) from file)
        var initialMessage = Files.readAllLines(Paths.get("digitalData.txt")).get(0);
        var signedDocument = Files.readAllLines(Paths.get("digitalData.txt")).get(1);
        ;
        // 5. verify extracted fields (message and encryptedHash)
        var authenticity = VerifyAuthenticity(signedDocument, initialMessage);
        if (authenticity) {
            return true;
        } else {
            return false;
        }
    }

    public static String GetDigest(String message) throws NoSuchAlgorithmException {

        MessageDigest hashAlgorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageByteArray = message.getBytes(StandardCharsets.UTF_8);
        byte[] hash = hashAlgorithm.digest(messageByteArray);
        BigInteger hashedString = new BigInteger(1, hash);

        return hashedString.toString(16);
    }

    public static boolean VerifyAuthenticity(String digitalSignature, String message) throws NoSuchAlgorithmException {
        //get hash of message
        var digest = GetDigest(message);
        //System.out.println(digest);
        var hash = RSA.Decrypt(digitalSignature);
        //System.out.println(hash);
        if (digest.equals(hash)) {
            return true;
        } else {
            return false;
        }
    }
}
