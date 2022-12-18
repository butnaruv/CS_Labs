package com.csLabs.jwtauthentication.Controller;

import com.csLabs.jwtauthentication.Service.ClassicalCiphers.CaesarCipher;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/user")
    @RolesAllowed("ROLE_USER")
    public String home() {
        return CaesarCipher.Encrypt("ana", 4);
    }

    @GetMapping("/caesar/{plaintext}/{key}")
    @RolesAllowed("ROLE_ADMIN")
    public String EncryptWithCaesarCipher(@PathVariable("plaintext") String plaintext, @PathVariable("key") int key) {
        CaesarCipher caesarCipher = new CaesarCipher();
        return caesarCipher.Encrypt(plaintext, key);
    }
}

