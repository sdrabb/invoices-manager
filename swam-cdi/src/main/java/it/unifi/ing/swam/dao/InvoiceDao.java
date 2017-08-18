package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Company;
import java.util.Date;
import java.util.List;

import it.unifi.ing.swam.model.Invoice;

public class InvoiceDao extends BaseDao<Invoice> {

    public InvoiceDao() {
        super(Invoice.class);
    }

    public List<Invoice> getInvoices() {

        String query = "SELECT e FROM Invoice e ORDER BY releaseDate ";

        List<Invoice> lastInvoices = entityManager
                .createQuery(query, Invoice.class)
                .setMaxResults(3)
                .getResultList();

        return lastInvoices;
    }

    public List<Invoice> findInvoicesByReleaseDate(Date releaseDate) {

        String query = "FROM Invoice WHERE releaseDate = :releaseDate";

        List<Invoice> result = entityManager
                .createQuery(query, Invoice.class)
                .setParameter("releaseDate", releaseDate)
                .setMaxResults(10)
                .getResultList();

        return result;
    }

    public List<Invoice> findInvoicesByBuyer(Company buyer) {

        String query = "SELECT i FROM Invoice i WHERE buyer = :buyer ";

        List<Invoice> result = entityManager
                .createQuery(query, Invoice.class)
                .setParameter("buyer", buyer)
                .setMaxResults(10)
                .getResultList();

        return result;
    }

    public void removeByInvoiceId(Long InvoiceId) {

        String query = "SELECT i FROM Invoice i WHERE i.id = :invoiceId";

        Invoice result = entityManager
                .createQuery(query, Invoice.class)
                .setParameter("invoiceId", InvoiceId)
                .getSingleResult();

        entityManager.remove(result);

    }

}
