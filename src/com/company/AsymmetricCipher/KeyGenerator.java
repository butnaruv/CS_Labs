package com.company.AsymmetricCipher;

import java.util.ArrayList;
import java.util.Random;

public class KeyGenerator {

    //compute all prime numbers between 0 and a specific value (limitOfPrimes)
    public static ArrayList<Integer> SieveOfEratosthenes(int limit) {
        ArrayList<Integer> listOfPrimes = new ArrayList<>();
        //initialize the list of primes with values  from 2 to limit
        for (int i = 2; i < limit; i++) {
            listOfPrimes.add(i);
        }
        //initialize index variable to take one by one element from the list until the last element is reached
        int index = 0;
        //iterate until reach the last element from the list
        while (listOfPrimes.get(index) != listOfPrimes.get(listOfPrimes.size() - 1)) {
            //initialize variable to stock the value of the list at the index
            int valueToCompare = listOfPrimes.get(index);
            //iterate the list and verify if it contains multiple of valueToCompare.
            // If it is the case, remove the elements that respect that condition.
            for (int i = 0; i < listOfPrimes.size(); i++) {
                if (listOfPrimes.get(i) % valueToCompare == 0 && listOfPrimes.get(i) != valueToCompare) {
                    listOfPrimes.remove(i);
                }
            }
            index++;
        }
        return listOfPrimes;
    }

    //generate a number which is less than the size of the list of prime numbers
    public static Integer RandomGenerator(ArrayList<Integer> list) {
        Random ran = new Random();
        int index = ran.nextInt(list.size());
        return list.get(index);
    }


    //compute greatest common divisor
    public static Integer GreatestCommonDivisor(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        int remainder = a % b;
        if (remainder == 0) {
            return b;
        } else {
            return GreatestCommonDivisor(b, remainder);
        }
    }

    //de adaugat cerinta ca i != de p si q
    public static Integer EncryptKey(Integer NValue, Integer Fi_N, int p, int q) {
        ArrayList<Integer> primeNumbersUntilN = SieveOfEratosthenes(NValue);
        Integer firstKey = 0;
        for (int i = 0; i < primeNumbersUntilN.size(); i++) {
            if (primeNumbersUntilN.get(i) < Fi_N && GreatestCommonDivisor(primeNumbersUntilN.get(i), Fi_N) == 1 && primeNumbersUntilN.get(i) != p && primeNumbersUntilN.get(i) != q) {
                firstKey = primeNumbersUntilN.get(i);
                break;
            }
        }
        return firstKey;
    }

    public static Integer DecryptKey(Integer encryptKey, Integer Fi_N) {
        //initialize a variable which helps to compute the multiple of Fi_N
        int decryptKey = 0;
        for (int i = 0; i < 10; i++) {
            int x = 1 + (i * Fi_N);
            if (x % encryptKey == 0) {
                decryptKey = x / encryptKey;
                break;
            }
        }
        //int decryptKey = ((multiple * Fi_N) + 1) / encryptKey ;
        return decryptKey;
    }
}


//    public static int limitOfPrimes = 10;
//    private static int p, q ;
//    public static int NValue;
//    public static int FiFunction;
//    public static int encryptKey;
//    public static int decryptKey;
//
//    private static ArrayList<Integer> primeNumbers = SieveOfEratosthenes();

//return encrypt key
//return decrypt key
//return Fi value
//return NValue
//    public static Integer KeyGenerator() {
//        //initialize a list with prime numbers
//        //primeNumbers = SieveOfEratosthenes();
//        System.out.println(primeNumbers);
//        //NValue = ComputeN();
//        //NValue = p * q;
//        System.out.println("NValue: " + NValue);
//        FiFunction = (p - 1) * (q - 1);
//        System.out.println("Fi Function: " + FiFunction);
//        encryptKey = EncryptKey(NValue, FiFunction, primeNumbers);
//        System.out.println(encryptKey);
//        decryptKey = DecryptKey(encryptKey,FiFunction);
//        System.out.println(decryptKey);
//        return 0;
//    }

//    //compute the second value from the pair of numbers
//    public static Integer ComputeN(int p, int q) {
////        p = RandomGenerator(primeNumbers);
////        System.out.println("p:" + p);
////        q = RandomGenerator(primeNumbers);
////        System.out.println("q: " + q);
//        return p * q;
//    }
