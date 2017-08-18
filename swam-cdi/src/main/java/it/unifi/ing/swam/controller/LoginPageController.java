package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class LoginPageController {

    @Inject
    private UserSessionBean userSession;

    @Inject
    private UserDao userDao;

    private User userData;

    public LoginPageController() {
        userData = new User(UUID.randomUUID().toString());
    }

    public boolean login() {
        User loggedUser = userDao.login(getUserData());
        if (loggedUser == null) {
            throw new RuntimeException("User with email \"" + getUserData().getEmail() + "\" not found");
        }

        userSession.setUserId(loggedUser.getId());
        return true;
    }

    public boolean logout() {
        userSession.setUserId(null);
        return true;
    }

    public User getUserData() {
        return userData;
    }

}
