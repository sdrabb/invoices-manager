package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class UserPermissionController {

    @Inject
    UserSessionBean userSession;

    @PostConstruct
    public void verifyLogin() {
        if (userSession.isLoggedIn()) {

            System.out.println("you are logged");

        } else {
            throw new RuntimeException("you are not logged in");
        }

    }
}
