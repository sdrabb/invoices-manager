package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ViewUserDataPageController extends UserPermissionController {

    @Inject
    private UserDao userDao;

    private User userData;

    public void retrieveUserDataFromDB() {

        userData = userDao.findById(userSession.getUserId());

    }

    public User getUserData() {
        return userData;
    }

}
