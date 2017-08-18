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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewUserDataPageControllerTest {

    private ViewUserDataPageController viewUserDataPageController;

    private UserSessionBean userSession;

    private UserDao userDao;

    private User userData;
    private User userDataMock;

    @Before
    public void setUp() throws InitializationError {

        viewUserDataPageController = new ViewUserDataPageController();

        userSession = new UserSessionBean();

        userData = new User(UUID.randomUUID().toString());
        userDataMock = new User(UUID.randomUUID().toString());

        userDao = mock(UserDao.class);

        try {
            FieldUtils.writeField(userData, "id", Long.valueOf(10), true);
            FieldUtils.writeField(userDataMock, "id", Long.valueOf(30), true);
            FieldUtils.writeField(userSession, "userId", Long.valueOf(20), true);
            FieldUtils.writeField(viewUserDataPageController, "userData", userData, true);
            FieldUtils.writeField(viewUserDataPageController, "userDao", userDao, true);
            FieldUtils.writeField(viewUserDataPageController, "userSession", userSession, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testRetrieveInvoiceDataFromDB() {
        when(userDao.findById(any(Long.class))).thenReturn(userDataMock);

        viewUserDataPageController.retrieveUserDataFromDB();

        assertEquals(userDataMock.getId(), viewUserDataPageController.getUserData().getId());

    }
}
