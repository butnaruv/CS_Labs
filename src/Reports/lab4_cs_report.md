# Laboratory Work nr. 4: Hash functions and Digital Signatures.

## Course: Cryptography & Security

## Author: Valeria Butnaru

---

## Theory

&ensp;&ensp;&ensp;  A digital signature is a cryptographic mechanism used to verify the authenticity and integrity of digital data. A signature confirms that the information originated from the signer and has not been altered.Technically speaking, a digital signature is the encrypted hash of a message. That means we generate a hash from a message and encrypt it with a private key according to a chosen algorithm.

The message, the encrypted hash, the corresponding public key, and the algorithm are all then sent. This is classified as a message with its digital signature.

---

## Objectives

1. Get familiar with the hashing techniques/algorithms.

2. Use an appropriate hashing algorithms to store passwords in a local DB.

3. Use an asymmetric cipher to implement a digital signature process for a user message.

## Implementation description

&ensp;&ensp;&ensp; 
* The first step for generating a digital signature is the conversion of the message in a hash. In order to obtain the hash, the algorithm  SHA-256 is used. It has a pre-build method in java which compute the hash of a message defined in bytes. Here is the piece of code which implements the conversion of the message in hash.

```
MessageDigest hashAlgorithm = MessageDigest.getInstance("SHA-256");
        byte[] messageByteArray = message.getBytes(StandardCharsets.UTF_8);
        byte[] hash = hashAlgorithm.digest(messageByteArray);
```
First a MessageDigest variable is instantiate, specifying the algorithm used for hashing. Then, the message is converted from string to bytes. At the end the array of bytes get converted into a hash.

* Next step is to encrypt the hash using the private key and RSA algorithm implemented before. 

* Now the original message and the encrypted hash are stored in a file which can be sent in oredr to test the authenticity of the digital data.
```        
FileWriter myWriter = new FileWriter("digitalData.txt");
myWriter.write(message + "\n");
myWriter.write(encryptedHash);
myWriter.close();
```

* When the digital data is sent, its authenticity should be verified. For this _VerifyAuthenticity()_ method is responsible. It has as input the digital signature (encrypted hash) and the original message.

```
var digest = GetDigest(message);
var hash = RSA.Decrypt(digitalSignature);
    if (digest.equals(hash)) {
         return true;
    } else {
        return false;
    }
```
In this method the message is converted in hash, and the encrypted hash is decrypted in order to obtain the hash value before encryption. If this two hash are equal, then the data is authentic.

## Conclusions 
This laboratory work represented an initiation into subject of digital signature and of authenticity of digital data. Being a mechanism which implements cryptografic skills, it was a pleasure to see how previous implementations of ciphers helped to generate a digital signature simulation. 