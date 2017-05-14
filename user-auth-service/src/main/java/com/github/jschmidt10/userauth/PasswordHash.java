package com.github.jschmidt10.userauth;

import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Creates salts and hashes for passwords.
 */
// TODO: Use keyed hashing.
class PasswordHash {

    private static final String HASH_ALG = "SHA-256";

    private SecureRandom random = new SecureRandom();

    /**
     * Create the users hashed password along with their salt.
     *
     * @param password
     * @return salt + hash
     */
    PasswordResult create(byte[] password) {
        try {
            byte[] salt = random.generateSeed(32);
            byte[] hash = hash(salt, password);

            return new PasswordResult(salt, hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Perform a hash with the given salt and password.
     *
     * @param salt
     * @param password
     * @return hash
     * @throws Exception
     */
    byte[] hash(byte[] salt, byte[] password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALG);
        digest.reset();
        digest.update(salt);
        return digest.digest(password);
    }

    /**
     * Results of creating a new password. Contains a salt and a hash.
     */
    static class PasswordResult {
        private byte[] salt;
        private byte[] passwordHash;

        PasswordResult(byte[] salt, byte[] passwordHash) {
            this.salt = salt;
            this.passwordHash = passwordHash;
        }

        byte[] getSalt() {
            return salt;
        }

        byte[] getPasswordHash() {
            return passwordHash;
        }
    }
}
