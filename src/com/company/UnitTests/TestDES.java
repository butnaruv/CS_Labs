package com.company.UnitTests;

import com.company.SymmetricCiphers.BlockCipher.DESCipher;

public class TestDES {
    public static void TestDesCipher(){
        String message = "creative";
        String key = "12345678";
        var encrypted = DESCipher.Encrypt(message, key);
        var decrypted = DESCipher.Decrypt(DESCipher.Encrypt(message, key), key);
        System.out.println("Encrypted Message: " + encrypted);
        System.out.println("Decrypted Message: " + decrypted);
        if(message.equals(decrypted)) System.out.println("Test passed successfull");

    }
}
