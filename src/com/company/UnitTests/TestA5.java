package com.company.UnitTests;

import com.company.SymmetricCiphers.StreamCipher.A5Cipher;

public class TestA5 {
        public static void TestA5Cipher() {
            String message = "good";
            var encrypted = A5Cipher.Encrypt(message);
            var decrypted = A5Cipher.Decrypt(A5Cipher.Encrypt(message));
            System.out.println("Encrypted Message: " + encrypted);
            System.out.println("Decrypted Message: " + decrypted);
            if (message.equals(decrypted)) System.out.println("Test passed successfull");
        }
}
