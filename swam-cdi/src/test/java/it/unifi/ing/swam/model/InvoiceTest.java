package it.unifi.ing.swam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {

    private Invoice invoice;

    private List<InvoiceOrder> orders;

    private InvoiceOrder order1;
    private InvoiceOrder order2;

    @Before
    public void setUp() {
        invoice = new Invoice(UUID.randomUUID().toString());

        order1 = new InvoiceOrder(UUID.randomUUID().toString());
        order2 = new InvoiceOrder(UUID.randomUUID().toString());

        orders = new ArrayList<InvoiceOrder>();
        orders.add(order1);
        orders.add(order2);

        invoice.setOrders(orders);

    }

    @Test
    public void testFindInvoiceOrderByUuid() {
        InvoiceOrder result = invoice.findInvoiceOrderByUuid(order1.getUuid());

        assertNotNull(result);
        assertEquals(result.getUuid(), order1.getUuid());

        result = invoice.findInvoiceOrderByUuid(UUID.randomUUID().toString());

        assertNull(result);

    }

    @Test
    public void testRemoveInvoiceOrderByUuid() {
        assertEquals(orders.size(), 2);

        boolean result = invoice.removeInvoiceOrderByUuid(order1.getUuid());

        assertTrue(result);
        assertEquals(orders.size(), 1);

        result = invoice.removeInvoiceOrderByUuid(UUID.randomUUID().toString());

        assertFalse(result);
        assertEquals(orders.size(), 1);
    }

}
