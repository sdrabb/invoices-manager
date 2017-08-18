package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditUserDataPageControllerTest {

    private EditUserDataPageController editUserDataPageController;
    private UserDao userDao;
    private UserSessionBean userSession;
    private User user;
    private User userData;

    @Before
    public void setUp() throws InitializationError {

        editUserDataPageController = new EditUserDataPageController();

        userDao = mock(UserDao.class);

        userSession = new UserSessionBean();

        user = new User(UUID.randomUUID().toString());
        user.setEmail("user@example.com");
        user.setPassword("pass");

        try {
            FieldUtils.writeField(userSession, "userId", Long.valueOf(10), true);
            FieldUtils.writeField(editUserDataPageController, "userData", userData, true);
            FieldUtils.writeField(editUserDataPageController, "userDao", userDao, true);
            FieldUtils.writeField(editUserDataPageController, "userSession", userSession, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testRetrieveUserDataFromDB() {
        when(userDao.findById(Long.valueOf(10))).thenReturn(user);

        editUserDataPageController.retrieveUserDataFromDB();

        assertEquals(editUserDataPageController.getUserData().getPassword(), user.getPassword());
        assertEquals(editUserDataPageController.getUserData().getEmail(), user.getEmail());
    }

}
