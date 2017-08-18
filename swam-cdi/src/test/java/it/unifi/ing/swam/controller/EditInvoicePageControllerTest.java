package it.unifi.ing.swam.controller;

import it.unifi.ing.swam.bean.UserSessionBean;
import it.unifi.ing.swam.dao.AccountabilityDao;
import it.unifi.ing.swam.dao.CompanyDao;
import it.unifi.ing.swam.dao.InvoiceDao;
import it.unifi.ing.swam.dao.ProductDao;
import it.unifi.ing.swam.dao.UserDao;
import it.unifi.ing.swam.model.Company;
import it.unifi.ing.swam.model.Invoice;
import it.unifi.ing.swam.model.InvoiceOrder;
import it.unifi.ing.swam.model.Product;
import it.unifi.ing.swam.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.InitializationError;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EditInvoicePageControllerTest {

    private EditInvoicePageController invoiceFormController;

    private UserSessionBean userSession;

    private UserDao userDao;
    private InvoiceDao invoiceDao;
    private ProductDao productDao;
    private CompanyDao companyDao;
    private AccountabilityDao accountabilityDao;

    private Invoice invoiceData;
    private InvoiceOrder orderData;

    private User userMock;

    private Invoice invoiceMock;

    private Product productMock1;
    private Product productMock2;
    private Company companyMock;

    private List<Product> productListMock;
    private List<Company> companyListMock;

    @Before
    public void setUp() throws InitializationError {

        invoiceFormController = new EditInvoicePageController();

        userSession = new UserSessionBean();

        invoiceDao = mock(InvoiceDao.class);
        companyDao = mock(CompanyDao.class);
        productDao = mock(ProductDao.class);
        userDao = mock(UserDao.class);
        accountabilityDao = mock(AccountabilityDao.class);

        invoiceData = new Invoice(UUID.randomUUID().toString());
        orderData = new InvoiceOrder(UUID.randomUUID().toString());

        invoiceMock = new Invoice(UUID.randomUUID().toString());

        userMock = new User(UUID.randomUUID().toString());

        productMock1 = new Product(UUID.randomUUID().toString());
        productMock2 = new Product(UUID.randomUUID().toString());
        productListMock = new ArrayList<Product>();
        productListMock.add(productMock1);
        productListMock.add(productMock2);

        companyMock = new Company(UUID.randomUUID().toString());
        companyListMock = new ArrayList<Company>();
        companyListMock.add(companyMock);

        try {
            FieldUtils.writeField(userSession, "userId", Long.valueOf(10), true);

            FieldUtils.writeField(invoiceMock, "id", Long.valueOf(20), true);

            FieldUtils.writeField(invoiceFormController, "userSession", userSession, true);
            FieldUtils.writeField(invoiceFormController, "invoiceId", "20", true);
            FieldUtils.writeField(invoiceFormController, "invoiceDao", invoiceDao, true);
            FieldUtils.writeField(invoiceFormController, "invoiceData", invoiceData, true);
            FieldUtils.writeField(invoiceFormController, "companyDao", companyDao, true);
            FieldUtils.writeField(invoiceFormController, "productDao", productDao, true);
            FieldUtils.writeField(invoiceFormController, "userDao", userDao, true);
            FieldUtils.writeField(invoiceFormController, "accountabilityDao", accountabilityDao, true);

        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }
    }

    @Test
    public void testSelectInvoiceNew() {

        try {
            FieldUtils.writeField(invoiceFormController, "invoiceId", null, true);
        } catch (IllegalAccessException e) {
        }

        invoiceFormController.selectInvoice();

        assertNotNull(invoiceFormController.getInvoiceData());
    }

    @Test
    public void testSelectInvoiceAlreadySetted() {
        when(invoiceDao.findById(any(Long.class))).thenReturn(invoiceMock);

        invoiceFormController.selectInvoice();

        assertEquals(invoiceFormController.getInvoiceData().getUuid(), invoiceMock.getUuid());
    }

    @Test
    public void testCreateNewOrderWithInvoiceDataSetted() {
        boolean result = invoiceFormController.createNewOrder();

        Assert.assertNotNull(invoiceFormController.getInvoiceOrderData());
        Assert.assertEquals(invoiceFormController.getCurrentOrderUuid(), invoiceFormController.getInvoiceOrderData().getUuid());
        assertTrue(result);
    }

    @Test
    public void testCreateNewOrderWithoutInvoiceDataSetted() {
        invoiceFormController.setInvoiceData(null);

        boolean result = invoiceFormController.createNewOrder();

        Assert.assertNull(invoiceFormController.getInvoiceOrderData());

        assertFalse(result);
    }

    @Test
    public void testAddProductToSelectedOrder() {
        when(productDao.findById(any(Long.class))).thenReturn(productMock1);

        invoiceData.getOrders().add(orderData);
        invoiceFormController.setCurrentOrderUuid(orderData.getUuid());

        invoiceFormController.setInvoiceOrderData(orderData);

        boolean result = invoiceFormController.addProductToSelectedOrder();

        assertEquals(invoiceFormController.getInvoiceData().getOrders().size(), 1);
        assertTrue(result);
    }

    @Test
    public void testRemoveSelectedOrder() {
        invoiceData.getOrders().add(orderData);
        invoiceFormController.setCurrentOrderUuid(orderData.getUuid());

        boolean result = invoiceFormController.removeSelectedOrder();

        assertTrue(result);
        assertEquals(invoiceFormController.getInvoiceData().getOrders().size(), 0);

    }

    @Test
    public void testSetOrderQuantity() {

        invoiceFormController.setCurrentQuantityData(7);

        invoiceData.getOrders().add(orderData);
        invoiceFormController.setCurrentOrderUuid(orderData.getUuid());

        boolean result = invoiceFormController.setOrderQuantity();

        assertTrue(result);
        assertEquals(invoiceFormController.getInvoiceData().findInvoiceOrderByUuid(invoiceFormController.getCurrentOrderUuid()).getInfoOrder().getQuantity(), 7);

    }

    @Test
    public void testGetAllProduct() {
        when(productDao.getProducts()).thenReturn(productListMock);

        boolean result = invoiceFormController.getAllProduct();

        assertEquals(invoiceFormController.getListProductData().size(), 2);
        assertTrue(result);
    }

    @Test
    public void testGetBuyerCompanies() {

        when(accountabilityDao.findCompaniesByUserAndAccountabilityTypeName(any(Long.class), any(String.class))).thenReturn(companyListMock);
        when(userDao.findById(any(Long.class))).thenReturn(userMock);

        boolean result = invoiceFormController.getBuyerCompanies();

        assertEquals(invoiceFormController.getListBuyerCompanyData().size(), 1);
        assertTrue(result);
    }

    @Test
    public void testSetBuyerCompany() {
        when(companyDao.findById(any(Long.class))).thenReturn(companyMock);

        boolean result = invoiceFormController.setBuyerCompany();

        assertEquals(invoiceFormController.getInvoiceData().getBuyer().getUuid(), companyMock.getUuid());
        assertTrue(result);
    }

    @Test
    public void testSetUserToInvoice() {
        when(userDao.findById(any(Long.class))).thenReturn(userMock);

        boolean result = invoiceFormController.setUserToInvoice();

        assertEquals(invoiceFormController.getInvoiceData().getUser().getUuid(), userMock.getUuid());
        assertTrue(result);
    }

}
