package br.com.brenoararipe;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class Wallet {

    PrivateKey privateKey;
    PublicKey publicKey;

    public Wallet() {
        generatekeyPair();
    }

    public void generatekeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpecatc ecSpec = new ECGenParameterSpec("prime192v1");

            // Initialize the key gen and generate a KeyPair
            keyGen.initialize(ecSpec, random);
            KeyPair keyPair = keyGen.generateKeyPair();

            //Set the public and private keys from the keyPair
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
