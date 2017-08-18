package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.User;
import java.util.UUID;
import javax.persistence.NoResultException;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

public class BaseDaoTest extends JpaTest {

    public class BasicTestEntityDao extends BaseDao<User> {

        public BasicTestEntityDao() {
            super(User.class);
        }
    }

    private BasicTestEntityDao basicTestEntityDao;
    private User user;

    @Override
    protected void init() throws InitializationError {

        user = new User(UUID.randomUUID().toString());
        user.setName("name_test");

        entityManager.persist(user);

        basicTestEntityDao = new BasicTestEntityDao();

        try {
            FieldUtils.writeField(basicTestEntityDao, "entityManager", entityManager, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testSave() {
        User anotherUser = new User(UUID.randomUUID().toString());
        anotherUser.setName("name_test_2");

        basicTestEntityDao.save(anotherUser);

        assertEquals(anotherUser, entityManager
                .createQuery("from User where uuid =:uuid", User.class)
                .setParameter("uuid", anotherUser.getUuid())
                .getSingleResult());

    }

    @Test
    public void testFindById() {
        User result = basicTestEntityDao.findById(user.getId());

        assertEquals(user.getId(), result.getId());
        assertEquals(user.getUuid(), result.getUuid());
        assertEquals(user.getName(), result.getName());
    }

    @Test(expected = NoResultException.class)
    public void testRemove() {
        User anotherUser = new User(UUID.randomUUID().toString());
        anotherUser.setName("name_test_2");

        entityManager.persist(anotherUser);

        basicTestEntityDao.remove(anotherUser);

        entityManager
                .createQuery("from User where uuid =:uuid", User.class)
                .setParameter("uuid", anotherUser.getUuid())
                .getSingleResult();
    }

}
