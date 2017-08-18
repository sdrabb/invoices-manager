package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Accountability;
import it.unifi.ing.swam.model.AccountabilityType;
import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.PartyType;
import it.unifi.ing.swam.model.User;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

public class AccountabilityDaoJpaTest extends JpaTest {

    private AccountabilityDao accountabilityDao;
    private Accountability accountability;

    private CompanyDao companyDao;
    private UserDao userDao;
    private AccountabilityTypeDao accountabilityTypeDao;
    private PartyTypeDao partyTypeDao;

    @Override
    protected void init() throws InitializationError {

        accountability = new Accountability(UUID.randomUUID().toString());
        accountabilityDao = new AccountabilityDao();

        companyDao = new CompanyDao();
        userDao = new UserDao();
        accountabilityTypeDao = new AccountabilityTypeDao();
        partyTypeDao = new PartyTypeDao();

        try {
            FieldUtils.writeField(accountabilityDao, "entityManager", entityManager, true);
            FieldUtils.writeField(companyDao, "entityManager", entityManager, true);
            FieldUtils.writeField(userDao, "entityManager", entityManager, true);
            FieldUtils.writeField(accountabilityTypeDao, "entityManager", entityManager, true);
            FieldUtils.writeField(partyTypeDao, "entityManager", entityManager, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }

    }

    @Test
    public void testFindCompaniesByUser() {

        User user = new User(UUID.randomUUID().toString());
        Company company = new Company(UUID.randomUUID().toString());

        PartyType userType = new PartyType(UUID.randomUUID().toString(), "User");
        PartyType companyType = new PartyType(UUID.randomUUID().toString(), "Company");

        partyTypeDao.save(userType);
        partyTypeDao.save(companyType);

        user.setPartyType(userType);
        userDao.save(user);
        company.setPartyType(companyType);
        companyDao.save(company);

        AccountabilityType accountabilityType = new AccountabilityType(UUID.randomUUID().toString());
        accountabilityType.setCommissioner(companyType);
        accountabilityType.setResponsible(userType);
        accountabilityTypeDao.save(accountabilityType);

        accountability.setAccountabilityType(accountabilityType);

        try {
            accountability.setAccountability(company, user);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

        accountabilityDao.save(accountability);

        List<Company> companies = accountabilityDao.findCompaniesByUser(user.getId());

        assertEquals(companies.size(), 1);
        assertEquals(company.getId(), companies.get(0).getId());

    }

    @Test
    public void testfindCompaniesByUserAndAccountabilityType() {

        User user = new User(UUID.randomUUID().toString());
        Company company = new Company(UUID.randomUUID().toString());

        PartyType userType = new PartyType(UUID.randomUUID().toString(), "User");
        PartyType companyType = new PartyType(UUID.randomUUID().toString(), "Company");
        partyTypeDao.save(userType);
        partyTypeDao.save(companyType);

        user.setPartyType(userType);
        userDao.save(user);
        company.setPartyType(companyType);
        companyDao.save(company);

        AccountabilityType accountabilityType = new AccountabilityType(UUID.randomUUID().toString());
        accountabilityType.setName("SellTo");
        accountabilityType.setCommissioner(companyType);
        accountabilityType.setResponsible(userType);
        accountabilityTypeDao.save(accountabilityType);

        accountability.setAccountabilityType(accountabilityType);

        try {
            accountability.setAccountability(company, user);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

        accountabilityDao.save(accountability);

        List<Company> companies = accountabilityDao.findCompaniesByUserAndAccountabilityTypeName(user.getId(), "SellTo");

        assertEquals(companies.size(), 1);
        assertEquals(company.getId(), companies.get(0).getId());

    }

    @Test
    public void testRemoveByCompany() {

        User user = new User(UUID.randomUUID().toString());
        Company company = new Company(UUID.randomUUID().toString());

        PartyType userType = new PartyType(UUID.randomUUID().toString(), "User");
        PartyType companyType = new PartyType(UUID.randomUUID().toString(), "Company");
        partyTypeDao.save(userType);
        partyTypeDao.save(companyType);

        user.setPartyType(userType);
        userDao.save(user);
        company.setPartyType(companyType);
        companyDao.save(company);

        AccountabilityType accountabilityType = new AccountabilityType(UUID.randomUUID().toString());
        accountabilityType.setName("SellTo");
        accountabilityType.setCommissioner(companyType);
        accountabilityType.setResponsible(userType);
        accountabilityTypeDao.save(accountabilityType);

        accountability.setAccountabilityType(accountabilityType);

        try {
            accountability.setAccountability(company, user);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
        }

        accountabilityDao.save(accountability);

        assertEquals(accountability, entityManager.createQuery("select a from Accountability a WHERE a.commissioner.id = :companyId", Accountability.class)
                .setParameter("companyId", company.getId())
                .getSingleResult());

        accountabilityDao.removeByCompany(company.getId());

        assertTrue(entityManager.createQuery("select a from Accountability a WHERE a.commissioner.id = :companyId", Accountability.class)
                .setParameter("companyId", company.getId())
                .getResultList().isEmpty());
    }

}
