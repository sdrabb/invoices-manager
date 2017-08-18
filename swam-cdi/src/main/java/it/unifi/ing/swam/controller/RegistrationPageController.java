package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class RegistrationPageController {

    @Inject
    private UserSessionBean userSession;

    @Inject
    private UserDao userDao;

    private User userData;

    public RegistrationPageController() {
        userData = new User(UUID.randomUUID().toString());
    }

    public boolean register() {

        User loggedUser = userDao.findByEmail(getUserData());

        if (loggedUser == null) {

            userDao.save(userData);
            userSession.setUserId(userData.getId());
            return true;
        } else {
            throw new RuntimeException("User with email \"" + getUserData().getEmail() + "\" already exists");
        }
    }

    public User getUserData() {
        return userData;
    }
}
