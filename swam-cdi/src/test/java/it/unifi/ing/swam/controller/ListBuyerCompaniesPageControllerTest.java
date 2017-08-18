package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.AccountabilityDao;
import it.unifi.ing.swam.model.Company;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListBuyerCompaniesPageControllerTest {

    private ListBuyerCompaniesPageController manageBuyerCompaniesPageController;

    private UserSessionBean userSession;

    private AccountabilityDao accountabilityDao;

    private Company company;
    private List<Company> companies;

    @Before
    public void setUp() throws InitializationError {
        manageBuyerCompaniesPageController = new ListBuyerCompaniesPageController();

        userSession = new UserSessionBean();

        accountabilityDao = mock(AccountabilityDao.class);

        company = new Company(UUID.randomUUID().toString());

        companies = new ArrayList<Company>();
        companies.add(company);

        try {
            FieldUtils.writeField(userSession, "userId", Long.valueOf(10), true);
            FieldUtils.writeField(company, "id", Long.valueOf(20), true);
            FieldUtils.writeField(manageBuyerCompaniesPageController, "accountabilityDao", accountabilityDao, true);
            FieldUtils.writeField(manageBuyerCompaniesPageController, "userSession", userSession, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testGetBuyerCompaniesFromDB() {
        when(accountabilityDao.findCompaniesByUserAndAccountabilityTypeName(Long.valueOf(10), "SellTo")).thenReturn(companies);
        manageBuyerCompaniesPageController.getBuyerCompaniesFromDB();
        assertEquals(company, manageBuyerCompaniesPageController.getListCompaniesData().get(0));
    }

    @Test
    public void testDeleteBuyerCompany() {
        boolean result = manageBuyerCompaniesPageController.deleteBuyerCompany(Long.valueOf(20));
        assertTrue(result);
    }

}
