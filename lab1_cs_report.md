# Laboratory Work nr. 1: Intro to Cryptography. Classical ciphers. Caesar cipher.

## Course: Cryptography & Security

## Author: Valeria Butnaru

---

## Theory

&ensp;&ensp;&ensp; Cryptography represents the science responsible for creating secure information transfer techniques. The two basic processes of Cryptography are encryption and decryption. Encryption is a technique of rewriting a message from a clear language into a list of arranged symbols using a speific key and following a specific logic. Decryption is the reverse process of encryption: The transformation of the list of symbols that is incomprehensible and meaningless into a clear message.

---

## Objectives

1. Get familiar with the basics of cryptography and classical ciphers.

2. Implement 4 types of the classical ciphers:

   - Clasic Caesar Cipher
   - Caesar cipher with substitution and permutation
   - Vigenere cipher,
   - Playfair cipher.

3. Structure the project in methods/classes/packages as neeeded.

---

## Implementation Description

1. ### <i> Classic Caesar cipher </i>

&ensp;&ensp;&ensp;Caesar cipher is one of the most simple and widely known encryption techniques with substitution. Its basic idea is the replacement of each letter in the message by a letter some fixed number of positions down the alphabet. Encryption and decryption using Caesar Cipher can be defined by next formulas:

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $$em = enc_{k}(x) = x + k (mod \; n),$$

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $$dm = dec_{k}(x) = x - k + n (mod \; n),$$

where:

- em: the encrypted message,
- dm: the decrypted message,
- x: input,
- k: key,
- n: size of the alphabet.

&ensp;&ensp;&ensp; Caesar Cipher technique is implemented in the class with the same name. It contains two methods, one for encryption and another one for decryption of the message. Both methods contains an if statement which check if the character is uppercase, lowercase or empty character, and according to the result, it call the appropriate method from the parent class. The class itself inherits methods from the LettersManipulation class, which implement the converting of both uppercase and lowercase characters to ascii code and vice-versa. Finally the result is computed using the formulas described above.

```
for (char character : message.toCharArray()) {
            if (Character.isLowerCase(character)) {
                indexOfEncryptedLetter = (LowerCharToNumber(character) + key) % 26;
                encryptedMessage.append(NumberToLowerChar(indexOfEncryptedLetter));
            } else if (Character.isUpperCase(character)) {
                indexOfEncryptedLetter = (UpperCharToNumber(character) + key) % 26;
                encryptedMessage.append(NumberToUpperChar(indexOfEncryptedLetter));
            } else {
                encryptedMessage.append(character);
            }
        }

```

2.  ### <i> Caesar cipher with permutation </i>

&ensp;&ensp;&ensp; Caesar cipher with permutation has the same operating principle as the first cipher described. The single difference consist in permutation of the alphabet, before the letters from the message are replaced. The permutation is done using a key of string type. This step is implemented in the StartTheNewAlphabet() method from CaesarCipherWithPermutation class. It return a set in which the letters will be stored without repetition. First the letters from the key will eneter the set:

```
HashSet<Character> NewAlphabet = new LinkedHashSet<Character>();
        for (char character : keyWord.toCharArray()) {
            NewAlphabet.add(character);
        }
```

&ensp;&ensp;&ensp;Then the set will be filled with the rest of the letters from the alphabet, which is stored in a list named Alphabet:

```
 for (int i = 0; i < 26; i++) {
            NewAlphabet.add(Alphabet.get(i));
        }
```

&ensp;&ensp;&ensp;Next, the same processes will be carried out as in the classic cipher, only that the indexes of the letters from the newly formed alphabet will be used, not from the classic one.
&ensp;&ensp;&ensp;

3.  ### <i> Vigenere cipher </i>

&ensp;&ensp;&ensp; Vigenere cipher is also a substitution cipher. It is more effective than Caesar cipher because it implies a multiple displacement of the letters. Usually, it works with a key-word which is concatenated until it reaches the length equal to the length of the message. This step is implemented using a for loop in ReplaceTheMessageWithTheKey() method which appends the key-word character by character. In order to concatenate the key such as its length being equal to the length of the message, it is used a variable which is reinitialized at zero when the last letter of the key is reached.

```
        int j = 0;
        for (int i = 0; i < message.length(); i++) {
            if (j == key.length()) {
                j = 0;
            }
            ...
        }
```

&ensp;&ensp;&ensp;The logic for the encrytion and decryption of the message can be expressed mathematically as follow:

&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $$c_{i} = m_{i} + k_{i}(mod\ n) $$
&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; $$m_{i} = c_{i} - k_{i}(mod\ n) $$

- $c_{i}$: the position in alphabet of the $i^{th}$ character from the encrypted message,
- $m_{i}$ : the position in alphabet of the $i^{th}$ character from the decrypted message,
- $k_{i}$ : the position in alphabet of the $i^{th}$ character from the key ,
- n: size of the alphabet.

&ensp;&ensp;&ensp;Based on these formulas the Encrypt() and Decrypt() methods were build. Using an if statement, the method test if the character is upper/lower case and perform the conversion in of it in position index from the alphabet. Then the formula is applied, and the index obtained is converted to the specific letter.

```
for (int i = 0; i < message.length(); i++) {
    if (Character.isLowerCase(message.charAt(i))) {
        indexOfLetter = (LowerCharToNumber(message.charAt(i)) + LowerCharToNumber(newMessage.charAt(i))) % 26;
         encryptedMessage.append(NumberToLowerChar(indexOfLetter));
    }
    ...
    }
```

4.  ### <i> Playfair cipher </i>

&ensp;&ensp;&ensp; The Playfair cipher is the first practical digraph subsitution cipher. This cipher is specific because it works on digraphs - pair of two letters. As in the other ciphers, the encryption and decryption method work with a key. In this case, the key shoul be a string. Using this string, the letters of it will be arranged in a 5x5 matrix, without repetition. The cells wich rest empty will be filled with letters which are missing in the string, arranged in alphabetical order. J and i will took the same cell of the matrix. In order to create the matrix, the PlayfairCipher class used AddLettersInList() and StartNewAlphabetList() methods from the parent class. Then, because the letter j will be in the same cell as i, the method ExcludeLetterJ() is implemented with a for loop:

```
for(int i = 0 ; i < NewAlphabet.size(); i++){
    if(NewAlphabet.get(i).equals('j')) {
        NewAlphabet.remove(i);
    }
}
```

&ensp;&ensp;&ensp; As the algorithm proposes to split the message in digraphs, a for loop which updates the index by 2 is required i order to test if there is a digraph having the two identical letters. If it has, an x should be inserted between them.

```
for (int i = 1; i < newMessage.length(); i += 2) {
            if (newMessage.charAt(i) == newMessage.charAt(i - 1)) {
                newMessage = newMessage.insert(i, 'x');
            }
        }
```

&ensp;&ensp;&ensp; The using the OddNumberOfLetters() method, the program will check if after the insertion of x (if needed), the number of letters is odd. If it is odd, then an x should be inserted at the end of the message.

```
static String OddNumberOfLetters(String message) {
    if (message.length() % 2 == 0) {
        return message;
    } else {
        return message += "x";
    }
}
```

&ensp;&ensp;&ensp; After all these manipulation of the message, the program will analyse each digraph. Using the 5x5 matrix, the program is able to compute the "coordinates" of the letters as follow:

```
Integer xFirstLetter = Table.get(Character.toString(message.charAt(i - 1))).get(0);
Integer yFirstLetter = Table.get(Character.toString(message.charAt(i - 1))).get(1);
Integer xSecondLetter = Table.get(Character.toString(message.charAt(i))).get(0);
Integer ySecondLetter = Table.get(Character.toString(message.charAt(i))).get(1);
```

&ensp;&ensp;&ensp; Then, the logic of the encryption itself is implemented:

- if the letters are in the same row, then each letter will be replaced by the letter to the right
- if the letters are in the same column, then each letter will be replaced by the letter bellow
- if the letters are in different rows and columns, the form a rectangle with the two letters and take the letters on the horizontal opposite corner of the rectangle.

```
 if (xFirstLetter == xSecondLetter) {
     encryptedMessage.append(table[xFirstLetter][(yFirstLetter + 1) % 5]).append(table[xSecondLetter][(ySecondLetter + 1) % 5]);
} else if (yFirstLetter == ySecondLetter) {
     encryptedMessage.append(table[(xFirstLetter + 1) % 5][yFirstLetter]).append(table[(xSecondLetter + 1) % 5][ySecondLetter]);
} else {
    encryptedMessage.append(table[xFirstLetter][ySecondLetter]).append(table[xSecondLetter][yFirstLetter]);
}
```

## Conclusion

&ensp;&ensp;&ensp; This laboratory work was an initiation in the field of cryptography. Thus, the aim of researching and implementing the encryption and decryption process using different classical ciphers was achieved.
