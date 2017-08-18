package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.Invoice;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class AdvancedSearchPageController extends UserPermissionController {

    @Inject
    private InvoiceDao invoiceDao;

    private Date dateData;
    private Company companyData;

    private boolean filterBydateFlag;

    public AdvancedSearchPageController() {
        super();
        dateData = new Date();
        companyData = new Company(UUID.randomUUID().toString());

        filterBydateFlag = true;

    }

    public List<Invoice> filterInvoices() {

        if (filterBydateFlag == true) {

            return invoiceDao.findInvoicesByReleaseDate(dateData);
        } else {
            return invoiceDao.findInvoicesByBuyer(companyData);
        }

    }

}
