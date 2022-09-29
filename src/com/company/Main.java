package com.company;

import javax.crypto.Cipher;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        String message = "Anna has apples";
        int key = 4;
        String keyword = "alibaba";
        System.out.println("Encrypted message using Caesar Cipher:  ");
        System.out.println(CaesarCipher.Encrypt(message, key));
        System.out.println("Encrypted message using Caesar Cipher with permutation:  ");
        System.out.println(CaesarCipherWithPermutation.Encrypt(message, keyword, key));
        System.out.println("Encrypted message using Vigenere Cipher:  ");
        System.out.println(VigenereCipher.Encrypt(message, keyword));
        System.out.println("Encrypted message using Playfair Cipher:  ");
        System.out.println(PlayfairCipher.Encrypt(message, keyword));
        System.out.println("Decrypted message using Caesar Cipher:  ");
        System.out.println(CaesarCipher.Decrypt("Erre lew ettpiw", key));
        System.out.println("Decrypted message using Caesar Cipher with permutation:  ");
        System.out.println(CaesarCipherWithPermutation.Decrypt("Crrc ncw cttdjw", keyword, key));
        System.out.println("Decrypted message using Vigenere Cipher:  ");
        System.out.println(VigenereCipher.Decrypt("Ayvb hbs aaxmet", keyword));
        System.out.println("Decrypted message using Playfair Cipher:  ");
        System.out.println(PlayfairCipher.Decrypt("ikkidcqinzmcfr", keyword));
    }
}







