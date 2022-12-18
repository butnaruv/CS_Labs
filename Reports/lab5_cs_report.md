# Laboratory Work nr. 5: Web Authentication & Authorisation.

## Course: Cryptography & Security

## Author: Valeria Butnaru

---

## Theory

&ensp;&ensp;&ensp;  In simple terms, authentication is the process of verifying who a user is, while authorization is the process of verifying what they have access to. Thus, access to a resource is protected by both authentication and authorization. If you can't prove your identity, you won't be allowed into a resource. And even if you can prove your identity, if you are not authorized for that resource, you will still be denied access.

The most common authentication factors are 
* Password-based authentication  
* Multi-factor authentication 
* Certificate-based authentication
* Token authentication

In this paper will be discussed token authentication, also called JWT authentication.



### JWT authentication

A JSON Web Token is an open method for representing claims securely between two parties. A JWT is a set of claims (JSON property–value pairs) that together make up a JSON object. It consists of three parts:
* Header : the place where the algorithm used in encryption of data is stored
* Payload : the place where the data to be sent is stored
* Signature : it is created by encrypting, with the algorithm specified in the header

The process of JWT authentication starts with the request made by a client in which he enters its credentials. If they are successfully authenticated the service will return a JWT. The client will then store the JWT and each subsequent request will pass it via the Authorization header. When the Server application receives the request with the JWT it will verify that it is a valid token and if it is will allow the request to continue.

---

## Objectives

1. Take classes from previous laboratory works and add them in a web service.
2. Implement basic authentication and MFA
3. Simulate user authorisation

## Implementation description

1. To get started a _SecurityConfig_ class is created in the config package. This class should have the next configuration:
* Disable Cross-Site Request Forgery (CSRF)
* The user should be authenticated for any request in the application.
* To have a JWT method reference
* HTTP session should never be created (the security context can not be obtained through HTTP session
)
* HTTP Basic Authentication support is enabled by default


2. In order to test the application, a list of users should be created. This step is implemented in the method users which return a list of InMemoryUserDetailsManager data type. The user is created throught the next snipet of code:

```
UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}pass")
                .roles("ADMIN")
                .build();
```
3. In order to create the signature of the JWT token, we should implement a _JwtEncoder()_ and _JwtDecoder()_ method. For encryption and decryption it is used the rsa algortihm, with the public and private key generated using the OpenSSL cryptographic library. The keys which are stored in two .pem files are accessed using _RsaKeyProperties_ class. Using the public key, a jwtDecoder can be created.

```
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeys.publicKey()).build();
    }
```

The encoder will be used to encode the signature  into a token and sign it using the private key. It is used in the class _TokenService_ which is responsible for generating and validating tokens. The tokens are generated based on some claims, which are accessed in the next lines of code:
```
JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("AUTHORITIES_KEY", authorities)
                .claim("scope", scope)
                .build();
```

Then using an JwtEncoder object named _encoder_, the token is generated:
```
encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
```

4. The last step in autentication part is creation of a controller responsible for this process. In this project, this controller is _AuthController_ which has a POST method through the user send its credentials and based on these, the token is generated.
```
@PostMapping("/token")
    public String Token(Authentication authentication) {
        String token = tokenService.generateToken(authentication);
        return token;
    }
```

5. Then using this token the user can access some specific endpoints according to its user role. An example of such an endpoint can be the next one, at which only users wiht ROLE_ADMIN can have access.

```
  @GetMapping("/caesar/{plaintext}/{key}")
    @RolesAllowed("ROLE_ADMIN")
    public String EncryptWithCaesarCipher(@PathVariable("plaintext") String plaintext, @PathVariable("key") int key) {
        CaesarCipher caesarCipher = new CaesarCipher();
        return caesarCipher.Encrypt(plaintext, key);
    }
```

## Conclusion:

This laboratory work was an introduction into authentication and authorisation processes. It was a great opportunity to develop skills to work with spring boot, especially with spring security framework. Also, because the laboratory work should be a web application, it was a perfect chance to get to know better the process of creating a web application.