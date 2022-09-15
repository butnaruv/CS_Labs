package com.company;

public class Main {

    public static void main(String[] args) {
        String s = "Ana are mere";
        int key = 8;
        String s1 = CaesarCipher.EncodingCaesarCipher(s, key);
        System.out.println(s1);
        String s2 = CaesarCipher.DecodingCaesarCipher(s1, key);
        System.out.println(s2);
    }

}







