package com.csLabs.jwtauthentication.Controller;

import com.csLabs.jwtauthentication.Service.ClassicalCiphers.CaesarCipher;
import com.csLabs.jwtauthentication.Service.ClassicalCiphers.PlayfairCipher;
import com.csLabs.jwtauthentication.Service.ClassicalCiphers.VigenereCipher;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EncryptController {
    @GetMapping("/caesar/{plaintext}/{key}")
    @RolesAllowed("ROLE_ADMIN")
    public String EncryptWithCaesarCipher(@PathVariable("plaintext") String plaintext, @PathVariable("key") int key) {
        CaesarCipher caesarCipher = new CaesarCipher();
        return caesarCipher.Encrypt(plaintext, key);
    }

    @GetMapping("/vigenere/{plaintext}/{key}")
    @RolesAllowed("ROLE_ADMIN")
    public String EncryptWithVigenereCipher(@PathVariable("plaintext") String plaintext, @PathVariable("key") String key) {
        VigenereCipher vigenereCipher = new VigenereCipher();
        return vigenereCipher.Encrypt(plaintext, key);
    }

    @GetMapping("/playfair/{plaintext}/{key}")
    @RolesAllowed("ROLE_USER")
    public String EncryptWithPlayfairCipher(@PathVariable("plaintext") String plaintext, @PathVariable("key") String key) {
        PlayfairCipher playfairCipher = new PlayfairCipher();
        return playfairCipher.Encrypt(plaintext, key);
    }
}

