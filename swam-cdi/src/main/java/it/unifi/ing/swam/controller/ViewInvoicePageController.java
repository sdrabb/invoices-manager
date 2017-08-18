package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.HttpParam;

import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.model.Invoice;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class ViewInvoicePageController extends UserPermissionController {

    @Inject
    private InvoiceDao invoiceDao;

    private Invoice invoiceData;

    @Inject
    @HttpParam("invoiceId")
    private String invoiceId;

    public void retrieveInvoiceDataFromDB() {
        invoiceData = invoiceDao.findById(Long.valueOf(invoiceId));
    }

    public Invoice getInvoiceData() {
        return invoiceData;
    }

}
