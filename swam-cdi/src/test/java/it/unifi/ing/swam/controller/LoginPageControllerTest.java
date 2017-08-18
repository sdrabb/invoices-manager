package it.unifi.ing.swam.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import java.util.UUID;

public class LoginPageControllerTest {

    private LoginPageController userLoginController;
    private UserDao userDao;
    private UserSessionBean userSession;
    private User user;

    @Before
    public void setUp() throws InitializationError {
        userLoginController = new LoginPageController();

        userDao = mock(UserDao.class);

        userSession = new UserSessionBean();

        user = new User(UUID.randomUUID().toString());
        user.setEmail("user@example.com");
        user.setPassword("pass");

        try {
            FieldUtils.writeField(user, "id", Long.valueOf(10), true);
            FieldUtils.writeField(userLoginController, "userDao", userDao, true);
            FieldUtils.writeField(userLoginController, "userSession", userSession, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testLogin() {
        when(userDao.login(any(User.class))).thenReturn(user);

        boolean result = userLoginController.login();

        assertTrue(result);
        assertEquals(user.getId(), userSession.getUserId());
        assertTrue(userSession.isLoggedIn());
    }

    @Test(expected = RuntimeException.class)
    public void testLoginError() {
        when(userDao.login(any(User.class))).thenReturn(null);

        userLoginController.login();
    }

    @Test
    public void testLogout() {
        when(userDao.login(any(User.class))).thenReturn(user);

        userLoginController.login();
        boolean result = userLoginController.logout();

        assertTrue(result);
        assertNull(userSession.getUserId());
        assertFalse(userSession.isLoggedIn());
    }

}
