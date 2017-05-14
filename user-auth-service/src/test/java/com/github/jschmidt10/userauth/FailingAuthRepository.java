package com.github.jschmidt10.userauth;

import org.springframework.stereotype.Repository;

/**
 * A repository that fails.
 */
@Repository
public class FailingAuthRepository implements UserAuthRepository {

    @Override
    public UserAuthRecord fetchByUsername(String username) {
        throw new RuntimeException("Repository error");
    }

    @Override
    public void save(String username, byte[] salt, byte[] password) {
    }
}
