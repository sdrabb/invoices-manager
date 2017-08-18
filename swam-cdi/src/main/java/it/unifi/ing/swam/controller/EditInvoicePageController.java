package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.HttpParam;

import it.unifi.ing.swam.dao.AccountabilityDao;
import it.unifi.ing.swam.dao.CompanyDao;
import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.dao.ProductDao;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.InfoOrder;
import it.unifi.ing.swam.model.Invoice;
import it.unifi.ing.swam.model.InvoiceOrder;
import it.unifi.ing.swam.model.Product;
import it.unifi.ing.swam.model.User;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ViewScoped
public class EditInvoicePageController extends UserPermissionController {

    @Inject
    @HttpParam("invoiceId")
    private String invoiceId;

    private Long selectedBuyerCompanyId;

    private Long productId;

    private String currentOrderUuid;

    @Inject
    private UserDao userDao;

    @Inject
    private InvoiceDao invoiceDao;

    @Inject
    private ProductDao productDao;

    @Inject
    private CompanyDao companyDao;

    @Inject
    private AccountabilityDao accountabilityDao;

    private Invoice invoiceData;

    private InvoiceOrder invoiceOrderData;

    private int currentQuantityData;

    private List<Product> listProductData;
    private List<Company> listBuyerCompanyData;

    public void selectInvoice() {

        if (this.invoiceId == null) {

            invoiceData = new Invoice(UUID.randomUUID().toString());

        } else {

            invoiceData = invoiceDao.findById(Long.valueOf(invoiceId));

        }

    }

    public boolean createNewOrder() {

        if (invoiceData != null) {

            invoiceOrderData = new InvoiceOrder(UUID.randomUUID().toString());

            invoiceData.addOrder(invoiceOrderData);

            currentOrderUuid = invoiceOrderData.getUuid();

            return true;
        } else {

            return false;
        }

    }

    public boolean addProductToSelectedOrder() {

        Product selectedProduct = productDao.findById(productId);

        invoiceData.findInvoiceOrderByUuid(currentOrderUuid).setProduct(selectedProduct);

        currentOrderUuid = null;

        return true;

    }

    public boolean removeSelectedOrder() {

        if (invoiceData.removeInvoiceOrderByUuid(currentOrderUuid)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean setOrderQuantity() {

        InfoOrder infoOrder = new InfoOrder();

        infoOrder.setQuantity(currentQuantityData);

        invoiceData.findInvoiceOrderByUuid(currentOrderUuid).setInfoOrder(infoOrder);

        return true;

    }

    public boolean getAllProduct() {

        listProductData = productDao.getProducts();
        return true;

    }

    public boolean getBuyerCompanies() {

        User loggedUser = userDao.findById(userSession.getUserId());

        listBuyerCompanyData = accountabilityDao.findCompaniesByUserAndAccountabilityTypeName(loggedUser.getId(), "SellTo");

        return true;

    }

    @Transactional
    public boolean saveInvoice() {

        invoiceDao.save(invoiceData);
        return true;

    }

    public boolean setBuyerCompany() {

        Company buyer = companyDao.findById(selectedBuyerCompanyId);
        invoiceData.setBuyer(buyer);
        return true;

    }

    public boolean setUserToInvoice() {

        User user = userDao.findById(userSession.getUserId());
        invoiceData.setUser(user);
        return true;

    }

    public Invoice getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(Invoice invoiceData) {
        this.invoiceData = invoiceData;
    }

    public InvoiceOrder getInvoiceOrderData() {
        return invoiceOrderData;
    }

    public void setInvoiceOrderData(InvoiceOrder invoiceOrderData) {
        this.invoiceOrderData = invoiceOrderData;
    }

    public int getCurrentQuantityData() {
        return currentQuantityData;
    }

    public void setCurrentQuantityData(int currentQuantityData) {
        this.currentQuantityData = currentQuantityData;
    }

    public List<Product> getListProductData() {
        return listProductData;
    }

    public void setListProductData(List<Product> listProductData) {
        this.listProductData = listProductData;
    }

    public List<Company> getListBuyerCompanyData() {
        return listBuyerCompanyData;
    }

    public void setListBuyerCompanyData(List<Company> listBuyerCompanyData) {
        this.listBuyerCompanyData = listBuyerCompanyData;
    }

    public String getCurrentOrderUuid() {
        return currentOrderUuid;
    }

    public void setCurrentOrderUuid(String currentOrderUuid) {
        this.currentOrderUuid = currentOrderUuid;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

}
