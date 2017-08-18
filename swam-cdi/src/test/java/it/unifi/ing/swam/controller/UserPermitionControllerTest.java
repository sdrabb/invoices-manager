package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

public class UserPermitionControllerTest {

    private UserPermissionController userPermissionController;

    private UserSessionBean userSession;

    @Before
    public void setUp() throws InitializationError {

        userPermissionController = new UserPermissionController();

        userSession = new UserSessionBean();

        try {
            FieldUtils.writeField(userSession, "userId", Long.valueOf(20), true);
            FieldUtils.writeField(userPermissionController, "userSession", userSession, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testVerifyLoginException() {
        userSession.setUserId(null);
        userPermissionController.verifyLogin();
    }

}
