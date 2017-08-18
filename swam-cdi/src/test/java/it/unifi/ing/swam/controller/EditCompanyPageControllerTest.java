package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.CompanyDao;
import it.unifi.ing.swam.model.Company;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditCompanyPageControllerTest {

    private EditCompanyPageController editCompanyPageController;
    private CompanyDao companyDao;
    private UserSessionBean userSession;
    private Company company;
    private Company companyData;

    @Before
    public void setUp() throws InitializationError {

        editCompanyPageController = new EditCompanyPageController();

        companyDao = mock(CompanyDao.class);

        userSession = new UserSessionBean();

        company = new Company(UUID.randomUUID().toString());

        try {
            FieldUtils.writeField(userSession, "userId", Long.valueOf(10), true);
            FieldUtils.writeField(editCompanyPageController, "companyId", "20", true);
            FieldUtils.writeField(editCompanyPageController, "companyData", companyData, true);
            FieldUtils.writeField(editCompanyPageController, "companyDao", companyDao, true);
            FieldUtils.writeField(editCompanyPageController, "userSession", userSession, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testRetrieveUserDataFromDB() {
        when(companyDao.findById(Long.valueOf(20))).thenReturn(company);

        editCompanyPageController.retrieveCompanyDataFromDB();

        assertEquals(editCompanyPageController.getCompanyData().getId(), company.getId());
        assertEquals(editCompanyPageController.getCompanyData().getUuid(), company.getUuid());
    }

}
