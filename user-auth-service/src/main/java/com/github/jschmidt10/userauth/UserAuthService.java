package com.github.jschmidt10.userauth;

import java.util.Arrays;

/**
 * The user management module is responsible for storing authentication records and validating authentication
 * attempts.
 */
public class UserAuthService {

    // TODO: Configurable encoding
    private static final String ENC = "UTF-8";

    private UserAuthRepository repository;
    private PasswordHash hasher = new PasswordHash();

    public UserAuthService(UserAuthRepository repository) {
        this.repository = repository;
    }

    /**
     * Create a new authentication record.
     *
     * @param username
     * @param password
     */
    public void create(String username, String password) throws Exception {
        try {
            PasswordHash.PasswordResult pwr = hasher.create(password.getBytes(ENC));
            if (pwr != null) {
                repository.save(username, pwr.getSalt(), pwr.getPasswordHash());
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not save authentication record for " + username, e);
        }
    }

    /**
     * Attempt to authenticate a user.
     *
     * @param username
     * @param password
     * @return user
     */
    public boolean authenticate(String username, String password) {
        UserAuthRecord authRec = repository.fetchByUsername(username);

        if (authRec != null) {
            try {
                byte[] hash = hasher.hash(authRec.getSalt(), password.getBytes(ENC));

                // TODO: track failed login attempts
                return Arrays.equals(hash, authRec.getPasswordHash());
            } catch (Exception e) {
                // TODO: metrics
            }
        }

        return false;
    }
}
