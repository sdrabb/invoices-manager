package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationPageControllerTest {

    private RegistrationPageController userRegistrationController;
    private UserDao userDao;
    private UserSessionBean userSession;
    private User user;
    private User userData;

    @Before
    public void setUp() throws InitializationError {
        userRegistrationController = new RegistrationPageController();

        userDao = mock(UserDao.class);

        userSession = new UserSessionBean();

        user = new User(UUID.randomUUID().toString());
        user.setEmail("user@example.com");
        user.setPassword("pass");

        userData = new User(UUID.randomUUID().toString());
        userData.setEmail("newuser@example.com");
        userData.setPassword("newpass");

        try {
            FieldUtils.writeField(user, "id", Long.valueOf(10), true);
            FieldUtils.writeField(userData, "id", Long.valueOf(20), true);
            FieldUtils.writeField(userRegistrationController, "userData", userData, true);
            FieldUtils.writeField(userRegistrationController, "userDao", userDao, true);
            FieldUtils.writeField(userRegistrationController, "userSession", userSession, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testRegister() {
        when(userDao.findByEmail(any(User.class))).thenReturn(null);

        boolean result = userRegistrationController.register();

        assertTrue(result);
        assertEquals(userData.getId(), userSession.getUserId());
    }

    @Test(expected = RuntimeException.class)
    public void testRegisterError() {
        when(userDao.findByEmail(any(User.class))).thenReturn(user);

        userRegistrationController.register();
    }

}
