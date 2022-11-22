# Laboratory Work nr. 3: Asymmetric Ciphers.

## Course: Cryptography & Security

## Author: Valeria Butnaru

---

## Theory

&ensp;&ensp;&ensp; Cryptography represents the science responsible for creating secure information transfer techniques. The two basic processes of Cryptography are encryption and decryption. Encryption is a technique of rewriting a message from a clear language into a list of arranged symbols using a speific key and following a specific logic. Decryption is the reverse process of encryption: The transformation of the list of symbols that is incomprehensible and meaningless into a clear message.

---

## Objectives:

1. Get familiar with the symmetric cryptography, stream and block ciphers.

2. Implement an example of a stream cipher.

3. Implement an example of a block cipher.

---

## Implementation Description

1. ### <i> RSA </i>

   &ensp;&ensp;&ensp; RSA or Rivest–Shamir–Adleman is an algorithm employed by modern computers to encrypt and decrypt messages. It is an asymmetric cryptographic algorithm. Asymmetric means that there are two different keys. This is also called public-key cryptography because one among the keys are often given to anyone. The other is the private key which is kept private. Three main steps should be followed in order to implement RSA.

   1. Prepare public and private key:

- Pick two prime numbers _(p, q)_

&ensp;&ensp;&ensp; In order to pick two prime numbers, we should first compute a list of prime numbers. Thus, using Sieve of Eratosthenes algorithm, a list of prime numbers is computed. Then, randomly two elemets are choosen from the list. The numbers should be different. In this step two methods are involved _SieveOfEratosthenes()_ method and _RandomGenerator()_ method.

- Compute the product of the two prime numbers:  
  $$N = p*q$$
- Compute $\phi(N)$ , which is the number of coprime numbers with _N_ from 1 to _N_. $\phi(N)$ can be computed using the formula:

$$ \phi(N) = (p-1)(q-1) , $$

where _p_ and _q_ are the numbers that form _N_

- Compute _e_ - the key used in encryption process (public key). It should satisfies the next two properties:
  - 1 < e < $\phi(N)$
  - e should be coprime with _N_ and $\phi(N)$
- We obtain the pair of number for encryption _(e, N)_

This step is implemented in the _encryptKey()_ method. This method call _GreatestCommonDivisor()_ method in order to test if the value chosen in the range from 1 to $\phi(N)$ satisfies also the second condition.

And in order to test if two number are coprimes, they should have the greatest common divisor equal to 1.

For chosing an appropriate value for the public key, the next for loop and if statement are implemented:

```
for (int i = 0; i < primeNumbersUntilN.size(); i++) {
            if (primeNumbersUntilN.get(i) < Fi_N && GreatestCommonDivisor(primeNumbersUntilN.get(i), Fi_N) == 1 && primeNumbersUntilN.get(i) != p && primeNumbersUntilN.get(i) != q) {
                firstKey = primeNumbersUntilN.get(i);
                break;
            }
        }
```

For loop is responsible for chosing a value from the list of prime numbebers. Then, in order to get a value that satisfies all the conditions for the public key, the if statement is implemented. It test if the number is less than $\phi(N)$, if the greatest common divisior of it and _N_ is 1 (in order to be coprime), and if it is different from _q_ and _p_ .

- Compute _d_ - the key used for decryption (private key). It should satisfie the next condition :
  $$ (d\*e) mod (\phi(N))= 1 $$

It's obviously that the remainder for the division $ (d * e) /\phi(N)$ should be equal to one. Thus, *d\* can be computed according to the next formula:
$$ d = (k /phi(N) + 1)/ e$$
for some integer _k_. This step is implemented in _decryptKey()_ method.

2. Encryption

- First, the message should be converted in decimal. Thus, each letter will be converted in its ascii value. For this process is responsible the next line of code:

```
bigIntegerDecimalCharacter = new BigInteger (String.valueOf((int) character ));
```

For this algorithm is essential to work with bigInteger values, because the numbers involved are very large and the Integer data type is not big enough.

- Then, having the public key _e_ and the modulo number _N_, the encryption of each letter can be performed according to the next formula:

$$ c = m ^ e (mod N), $$

where:

- _c_ is decimal value of letter of cipher text
- _m_ is decimal value of letter of plain text
- _e_ is the public key
- _N_ is the modulo number (composed using _p_ and _q_)

* Now the numbers are ready to be converted into letters. Then using concatenating process of a string, each letter is added to the string. This is how the message is encrypted.

3. Decryption

- As in encryption process, the cipher message should be converted in decimal. Thus, each letter will be converted in its ascii value.

- Then, having the secret key _d_ and the modulo number _N_, the encryption of each letter can be performed according to the next formula:

$$ m = c ^ e (mod N), $$

where:

- _c_ is decimal value of letter of cipher text
- _m_ is decimal value of letter of plain text
- _d_ is the private key
- _N_ is the modulo number (composed using _p_ and _q_)

* Now the numbers are ready to be converted into letters. Then using concatenating process of a string, each letter is added to the string. This is how the message is decrypted.
