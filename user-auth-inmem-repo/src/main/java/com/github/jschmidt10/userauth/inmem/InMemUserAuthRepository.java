package com.github.jschmidt10.userauth.inmem;

import com.github.jschmidt10.userauth.UserAuthRecord;
import com.github.jschmidt10.userauth.UserAuthRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory authentication repository. Just for testing.
 */
@Repository
public class InMemUserAuthRepository implements UserAuthRepository {

    private Map<String, UserAuthRecord> records = new HashMap<>();

    @Override
    public UserAuthRecord fetchByUsername(String username) {
        return records.get(username);
    }

    @Override
    public void save(String username, byte[] salt, byte[] password) {
        records.put(username, new UserAuthRecord(username, salt, password));
    }
}
