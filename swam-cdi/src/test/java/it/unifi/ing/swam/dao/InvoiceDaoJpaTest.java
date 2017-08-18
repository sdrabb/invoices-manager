package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.Invoice;
import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

public class InvoiceDaoJpaTest extends JpaTest {

    private InvoiceDao invoiceDao;
    private Invoice invoice;

    private CompanyDao companyDao;

    @Override
    protected void init() throws InitializationError {
        invoice = new Invoice(UUID.randomUUID().toString());
        invoice.setReleaseDate(new Date());

        companyDao = new CompanyDao();

        entityManager.persist(invoice);

        invoiceDao = new InvoiceDao();

        try {
            FieldUtils.writeField(invoiceDao, "entityManager", entityManager, true);
            FieldUtils.writeField(companyDao, "entityManager", entityManager, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }

    }

    @Test
    public void testGetInvoices() {
        Invoice inv1 = new Invoice(UUID.randomUUID().toString());
        Invoice inv2 = new Invoice(UUID.randomUUID().toString());

        inv1.setReleaseDate(new Date());
        inv2.setReleaseDate(new Date());

        invoiceDao.save(inv1);
        invoiceDao.save(inv2);

        List<Invoice> lastInvoices = invoiceDao.getInvoices();

        assertEquals(lastInvoices.size(), 3);
        assertEquals(inv2.getId(), lastInvoices.get(lastInvoices.size() - 1).getId());
        assertEquals(inv1.getId(), lastInvoices.get(1).getId());
        assertEquals(invoice.getId(), lastInvoices.get(0).getId());
    }

    @Test
    public void testfindInvoicesByReleaseDate() {

        Invoice inv1 = new Invoice(UUID.randomUUID().toString());
        Invoice inv2 = new Invoice(UUID.randomUUID().toString());

        Date date1 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        Date date2 = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();

        inv1.setReleaseDate(date1);
        inv2.setReleaseDate(date2);

        invoiceDao.save(inv1);
        invoiceDao.save(inv2);

        List<Invoice> invoices = invoiceDao.findInvoicesByReleaseDate(date1);

        assertEquals(inv1.getReleaseDate(), invoices.get(0).getReleaseDate());
        assertEquals(inv2.getReleaseDate(), invoices.get(1).getReleaseDate());
    }

    @Test
    public void testFindInvoicesByBuyer() {
        Invoice inv1 = new Invoice(UUID.randomUUID().toString());
        Invoice inv2 = new Invoice(UUID.randomUUID().toString());
        Invoice inv3 = new Invoice(UUID.randomUUID().toString());

        Company company1 = new Company(UUID.randomUUID().toString());
        Company company2 = new Company(UUID.randomUUID().toString());

        inv1.setBuyer(company1);
        inv2.setBuyer(company1);

        inv3.setBuyer(company2);

        companyDao.save(company1);
        companyDao.save(company2);

        invoiceDao.save(inv1);
        invoiceDao.save(inv2);
        invoiceDao.save(inv3);

        List<Invoice> invoices = invoiceDao.findInvoicesByBuyer(company1);

        assertEquals(invoices.size(), 2);
        assertEquals(inv1.getId(), invoices.get(0).getId());
        assertEquals(inv2.getId(), invoices.get(1).getId());
    }

    @Test
    public void removeByInvoiceId() {
        Long invoiceID = invoice.getId();

        Invoice beforeInvoice = invoiceDao.findById(invoice.getId());

        invoiceDao.removeByInvoiceId(invoice.getId());

        Invoice result = invoiceDao.findById(invoice.getId());

        assertEquals(result, null);
        assertEquals(beforeInvoice.getId(), invoiceID);

    }
}
