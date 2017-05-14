package com.github.jschmidt10.userauth;

/**
 * A repository capable of creating user auth records and verifying login attempts.
 */
public interface UserAuthRepository {

    /**
     * Fetches an authentication record by username.
     *
     * @param username
     * @return user's authentication record.
     */
    UserAuthRecord fetchByUsername(String username);

    /**
     * Saves a new authentication record.
     *
     * @param username
     * @param salt
     * @param password
     */
    void save(String username, byte[] salt, byte[] password);
}
