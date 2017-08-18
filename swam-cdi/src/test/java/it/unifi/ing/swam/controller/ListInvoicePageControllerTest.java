package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.model.Invoice;
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

public class ListInvoicePageControllerTest {

    private ListInvoicePageController manageInvoicePageController;

    private UserSessionBean userSession;

    private InvoiceDao invoiceDao;

    private List<Invoice> invoices;
    private Invoice invoice;

    @Before
    public void setUp() throws InitializationError {

        manageInvoicePageController = new ListInvoicePageController();

        userSession = new UserSessionBean();

        invoiceDao = mock(InvoiceDao.class);

        invoice = new Invoice(UUID.randomUUID().toString());

        invoices = new ArrayList<Invoice>();
        invoices.add(invoice);

        try {
            FieldUtils.writeField(userSession, "userId", Long.valueOf(10), true);
            FieldUtils.writeField(invoice, "id", Long.valueOf(20), true);
            FieldUtils.writeField(manageInvoicePageController, "invoiceDao", invoiceDao, true);
            FieldUtils.writeField(manageInvoicePageController, "userSession", userSession, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testGetLastInvoicesFromDB() {
        when(invoiceDao.getInvoices()).thenReturn(invoices);
        manageInvoicePageController.getInvoicesFromDB();
        assertEquals(invoice, manageInvoicePageController.getLastInvoicesData().get(0));
    }

    @Test
    public void testDeleteSelectedInvoice() {
        boolean result = manageInvoicePageController.deleteSelectedInvoice(Long.valueOf(20));
        assertTrue(result);
    }

}
