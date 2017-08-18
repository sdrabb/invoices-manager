package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.User;
import java.util.List;

public class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User findByEmail(User user) {
        String query = "FROM User WHERE email = :email";

        User result = entityManager
                .createQuery(query, User.class)
                .setParameter("email", user.getEmail())
                .getSingleResult();

        return result;
    }

    public User login(User user) {
        List<User> result = entityManager
                .createQuery("from User "
                        + "where email = :email "
                        + "and password = :pass", User.class)
                .setParameter("email", user.getEmail())
                .setParameter("pass", user.getPassword())
                .setMaxResults(1)
                .getResultList();

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

}
