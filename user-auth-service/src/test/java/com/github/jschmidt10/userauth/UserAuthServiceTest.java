package com.github.jschmidt10.userauth;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserAuthServiceTest {

    private UserAuthService service = new UserAuthService(new InMemUserAuthRepository());

    private String username = "scott";
    private String password = "tiger";
    private String wrongPassword = "tigger";

    @Before
    public void setup() throws Exception {
        service.create(username, password);
    }

    @Test
    public void shouldAuthenticateASavedUser() {
        assertThat(service.authenticate(username, password), is(true));
    }

    @Test
    public void shouldNotAuthenticateInvalidPassword() {
        assertThat(service.authenticate(username, wrongPassword), is(false));
    }

    @Test(expected = Exception.class)
    public void shouldPropogateAnyRepositoryExceptions() throws Exception {
        service = new UserAuthService(new FailingAuthRepository());
        service.create(username, password);
        service.authenticate(username, wrongPassword);
    }
}
