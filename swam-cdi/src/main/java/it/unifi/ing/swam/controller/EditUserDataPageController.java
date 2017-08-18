package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.User;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ViewScoped
public class EditUserDataPageController extends UserPermissionController {

    @Inject
    private UserDao userDao;

    private User userData;

    public EditUserDataPageController() {
        super();
    }

    public void retrieveUserDataFromDB() {

        userData = userDao.findById(userSession.getUserId());

    }

    public User getUserData() {
        return userData;
    }

    @Transactional
    public boolean saveUserData() {

        userDao.save(userData);

        return true;

    }

}
