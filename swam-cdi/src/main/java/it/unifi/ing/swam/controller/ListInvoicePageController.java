package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.model.Invoice;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ListInvoicePageController extends UserPermissionController {

    @Inject
    private InvoiceDao invoiceDao;

    private List<Invoice> lastInvoicesData;

    public List<Invoice> getLastInvoicesData() {
        return lastInvoicesData;
    }

    public ListInvoicePageController() {
        super();

    }

    public void getInvoicesFromDB() {
        lastInvoicesData = invoiceDao.getInvoices();
    }

    public boolean deleteSelectedInvoice(Long selectedInvoiceId) {
        invoiceDao.removeByInvoiceId(selectedInvoiceId);
        return true;
    }

}
