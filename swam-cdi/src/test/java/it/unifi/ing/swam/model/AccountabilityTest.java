package it.unifi.ing.swam.model;

import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AccountabilityTest {

    private Company company;
    private User user;
    private PartyType userType;
    private PartyType companyType;
    private AccountabilityType accountabilityType;
    private Accountability accountability;

    @Before
    public void setUp() {
        user = new User(UUID.randomUUID().toString());
        company = new Company(UUID.randomUUID().toString());

        userType = new PartyType(UUID.randomUUID().toString(), "User");
        companyType = new PartyType(UUID.randomUUID().toString(), "Company");

        user.setPartyType(userType);
        company.setPartyType(companyType);

        accountabilityType = new AccountabilityType(UUID.randomUUID().toString());
        accountabilityType.setCommissioner(companyType);
        accountabilityType.setResponsible(userType);

        accountability = new Accountability(UUID.randomUUID().toString());
        accountability.setAccountabilityType(accountabilityType);
    }

    @Test
    public void testCheckAccountability() {
        boolean result = accountability.checkAccountability(company, user);

        assertTrue(result);
    }

    @Test
    public void testCheckAccountabilityFailed() {
        boolean result = accountability.checkAccountability(user, company);

        assertFalse(result);
    }

    @Test
    public void testSetAccountability() {
        accountability.setAccountability(company, user);

        assertEquals(accountability.getResponsible(), user);
        assertEquals(accountability.getCommissioner(), company);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAccountabilityException() {
        accountability.setAccountability(user, company);

    }
}
