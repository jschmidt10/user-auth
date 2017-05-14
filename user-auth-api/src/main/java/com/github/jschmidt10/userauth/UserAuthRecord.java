package com.github.jschmidt10.userauth;

/**
 * A record from the UserAuthRepository.
 */
public class UserAuthRecord {

    private String username;
    private byte[] salt;
    private byte[] passwordHash;

    public UserAuthRecord(String username, byte[] salt, byte[] hashedPassword) {
        this.username = username;
        this.salt = salt;
        this.passwordHash = hashedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }
}
