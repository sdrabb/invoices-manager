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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewCompanyPageControllerTest {

    private ViewCompanyPageController viewCompanyPageController;

    private UserSessionBean userSession;

    private CompanyDao companyDao;

    private Company companyData;
    private Company companyDataMock;

    @Before
    public void setUp() throws InitializationError {

        viewCompanyPageController = new ViewCompanyPageController();

        userSession = new UserSessionBean();

        companyData = new Company(UUID.randomUUID().toString());
        companyDataMock = new Company(UUID.randomUUID().toString());

        companyDao = mock(CompanyDao.class);

        try {
            FieldUtils.writeField(companyData, "id", Long.valueOf(10), true);
            FieldUtils.writeField(companyDataMock, "id", Long.valueOf(30), true);
            FieldUtils.writeField(userSession, "userId", Long.valueOf(20), true);
            FieldUtils.writeField(viewCompanyPageController, "companyId", "30", true);
            FieldUtils.writeField(viewCompanyPageController, "companyData", companyData, true);
            FieldUtils.writeField(viewCompanyPageController, "companyDao", companyDao, true);
            FieldUtils.writeField(viewCompanyPageController, "userSession", userSession, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testRetrieveCompanyDataFromDB() {
        when(companyDao.findById(any(Long.class))).thenReturn(companyDataMock);

        viewCompanyPageController.retrieveCompanyDataFromDB();

        assertEquals(companyDataMock.getId(), viewCompanyPageController.getCompanyData().getId());

    }

}
