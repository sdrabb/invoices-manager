package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.model.Invoice;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ViewInvoicePageControllerTest {

    private ViewInvoicePageController invoiceViewController;

    private UserSessionBean userSession;

    private InvoiceDao invoiceDao;

    private Invoice invoiceData;
    private Invoice invoiceDataMock;

    @Before
    public void setUp() throws InitializationError {

        invoiceViewController = new ViewInvoicePageController();

        userSession = new UserSessionBean();

        invoiceData = new Invoice(UUID.randomUUID().toString());
        invoiceDataMock = new Invoice(UUID.randomUUID().toString());

        invoiceDao = mock(InvoiceDao.class);

        try {
            FieldUtils.writeField(invoiceData, "id", Long.valueOf(10), true);
            FieldUtils.writeField(invoiceDataMock, "id", Long.valueOf(30), true);
            FieldUtils.writeField(userSession, "userId", Long.valueOf(20), true);
            FieldUtils.writeField(invoiceViewController, "invoiceData", invoiceData, true);
            FieldUtils.writeField(invoiceViewController, "invoiceId", "30", true);
            FieldUtils.writeField(invoiceViewController, "invoiceDao", invoiceDao, true);
            FieldUtils.writeField(invoiceViewController, "userSession", userSession, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testRetrieveInvoiceDataFromDB() {
        when(invoiceDao.findById(any(Long.class))).thenReturn(invoiceDataMock);

        invoiceViewController.retrieveInvoiceDataFromDB();

        assertEquals(invoiceDataMock.getId(), invoiceViewController.getInvoiceData().getId());

    }

}
