# Laboratory Work nr. 2: Symmetric Ciphers. Stream Ciphers. Block Ciphers.

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

1. ### <i> Stream Cipher (A5/1) </i>

&ensp;&ensp;&ensp;A stream cipher is a symmetric key cipher where plaintext digits are combined with a pseudorandom cipher digit stream (keystream). In a stream cipher, each plaintext digit is encrypted one at a time with the corresponding digit of the keystream, to give a digit of the ciphertext stream.

&ensp;&ensp;&ensp;The A5/1 is a stream cipher of 7 steps which described below:
1. Conversion of message from letters to binary.

&ensp;&ensp;&ensp; Each letter of the message will be converted into an integer value. Then, using modulo 2 operation, the integer value will be converted into an 8 bit binary value, which should be inverted. All the values will be added to a list.
All the methods responsible for this step are implemented in the class LettersToBinaryConverter.


2. Initialization of 3 Linear-Feedback Shift Registers (LFSR) of different size with value of 0:
   + LFSR1 - 18 bits;
   + LFSR2 - 22 bits;
   + LFSR3 - 23 bits.
3. Random generation of a session key of 64 bits
4. For each LFSR, performing a xor operation between feedback bits and each bit of session key.
   + Feedback bits of LFSR1 are: bit 13, bit 16, bit 17, bit 18;
   + Feedback bits of LFSR2 are: bit 20, bit 21;
   + Feedback bits of LFSR3 are: bit 7, bit 20, bit 21, bit 22.


&ensp;&ensp;&ensp;The xor operation is performed 64 times, such that each bit of the key stream is used. 
Each time when a xor operation is performed, the register is shifted one space to the right and the computed value from xor is added to the register in the first position(LFSR[0]).
5. Random generation of a frame counter of 22 bits.
6. For each LFSR performing a xor operations between feedback bits and each bit of frame counter, such as in step 4.
7. Computation of the majority of clocking bits of registers
   + Clocking bit of LFSR1 - bit 8;
   + Clocking bit of LFSR2 - bit 10;
   + CLocking bit of LFSR3 - bit 10.


&ensp;&ensp;&ensp; The majority represents the bit that repeats most times.
8. Clocking the register

&ensp;&ensp;&ensp; If the clocking bit of the LFSR is equal to the majority computed, then the register is clocked. Clocking of the register represent the xor operation of feedback bits.
After computing this value, the register is shifted right with one position, and the first position will be completed with the value from the xor operation. If the majority and the clocking bit of the register are different, then the register does not suffer any changes. 
This step will be executed 100 times.
9. Generation of key stream

&ensp;&ensp;&ensp; The step 8 will be now executed 228 times, but in addition to it, the last bit of the registers will be xored, and te result will be add in an list of bits that will represent the key stream.
10. Encryption

&ensp;&ensp;&ensp;The encryption will be performed using xor operation between each digit from the binary message and each bit of the key stream. If the message is bigger than the key stream, it will be split into lists of 228 bits, and each of them will be xored with the key stream.

11. Conversion of binary message into letters

&ensp;&ensp;&ensp; The last step is conversion of the list of binary digits into letters. First, the list will be split in pieces of 8 digits, and it will be converted in integer values. Than, this values will be converted into letters, according to ASCII table.

```

```


2. ### <i> Block Cipher (DES) </i>

&ensp;&ensp;&ensp;The Data Encryption Standard (DES) is a symmetric-key block cipher published by the National Institute of Standards and Technology (NIST).

DES is an implementation of a Feistel Cipher. It uses 16 round Feistel structure. The block size is 64-bit. DES cipher has 4 phases which will be described below

1. Phase 1: Key manipulation.


   &ensp;&ensp;&ensp; Key manipulation imply manipulation of the key and generation of 16 sub keys, which will be involved in round phase. 
   
&ensp;&ensp;&ensp;The key accepted in DES cipher is a string of 8 characters, which will generate a 64 bit sequence of 0's and 1's. The key is converted in binary through _ConvertListToIntegers()_ method from _LettersToBinaryConverter_ class. 

Then, the key is permuted according to PC1 table through _PermuteList()_ method. The basic idea of this method is to rearrange the elements of the list in the order indicated in the rule table - PC1 in this case. Thus, the rule table is iterated, and at each iteration the element from the list at the index extracted from the rule table is added to a new permuted list:


```
for (int i = 0; i < permuteRule.size(); i++) {
   int TableIndex = permuteRule.get(i);
   int valueSelected = binaryList.get(TableIndex - 1);
   PermutedList.add(valueSelected);
   }
```
After permutation, the key is divided into two equal parts which are round shifted 16 times to the left, in order to generate 16 sub keys. To generate first, second, the ninth and the sixth key the shift is performed once. In the other cases the shift function is called twice.
The shift function is based on the function _ShiftLogic()_ in which is implemented the logic of round shifting: 
* the first element of the initial list is saved in a variable
* the list is iterated starting with element the second element (at position 1)
* the value of the element from the index equal to the iteration number is shifted one position to the left
* the first element now is added at the end of the list

````
Integer firstElement = list.get(0);
for (int i = 1; i < list.size(); i++) {
   Integer elementToBeShifted = list.get(i);
   list.add(i - 1, elementToBeShifted);
}
list.add(firstElement);
````

After each shift, the two parts are concatenated, thus a new list of 56 bits is returned. This shifted list is now permuted according to PC2 table rule. The permutation is performed on each of the 16 keys generated through shifting.
2. Phase 2: Message permutation and division
3. Phase 3: Round phase
4. Phase 4: Final permutation
