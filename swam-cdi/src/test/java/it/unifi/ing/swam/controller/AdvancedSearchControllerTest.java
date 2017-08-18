package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.Invoice;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

public class AdvancedSearchControllerTest {

    private AdvancedSearchPageController invoiceAdvancedSearchController;

    private UserSessionBean userSession;

    private List<Invoice> invoices;
    private InvoiceDao invoiceDao;

    private boolean filterBydateFlag;
    private Long selectedInvoiceId;

    @Before
    public void setUp() throws InitializationError {

        invoiceAdvancedSearchController = new AdvancedSearchPageController();

        filterBydateFlag = true;

        userSession = new UserSessionBean();

        userSession.setUserId(Long.valueOf(10));

        invoices = new ArrayList<Invoice>();

        Invoice inv1 = new Invoice(UUID.randomUUID().toString());

        Invoice inv2 = new Invoice(UUID.randomUUID().toString());

        invoices.add(inv1);
        invoices.add(inv2);

        invoiceDao = mock(InvoiceDao.class);

        try {
            FieldUtils.writeField(invoiceAdvancedSearchController, "invoiceDao", invoiceDao, true);
            FieldUtils.writeField(invoiceAdvancedSearchController, "userSession", userSession, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testFilterInvoicesByDate() {

        filterBydateFlag = true;

        when(invoiceDao.findInvoicesByReleaseDate(any(Date.class))).thenReturn(invoices);

        List<Invoice> result = invoiceAdvancedSearchController.filterInvoices();

        assertEquals(result.size(), invoices.size());

    }

    @Test
    public void testFilterInvoicesByBuyer() {
        filterBydateFlag = false;

        when(invoiceDao.findInvoicesByBuyer(any(Company.class))).thenReturn(new ArrayList<Invoice>());

        List<Invoice> result = invoiceAdvancedSearchController.filterInvoices();

        assertEquals(result.size(), 0);

    }

}
