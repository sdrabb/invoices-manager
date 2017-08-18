package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Product;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.reflect.FieldUtils;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runners.model.InitializationError;

public class ProductDaoJpaTest extends JpaTest {

    private ProductDao productDao;

    @Override
    protected void init() throws InitializationError {

        productDao = new ProductDao();

        try {
            FieldUtils.writeField(productDao, "entityManager", entityManager, true);
        } catch (IllegalAccessException e) {
            throw new InitializationError(e);
        }

    }

    @Test
    public void testGetProducts() {
        Product product1 = new Product(UUID.randomUUID().toString());
        Product product2 = new Product(UUID.randomUUID().toString());

        productDao.save(product1);
        productDao.save(product2);

        List<Product> products = productDao.getProducts();

        assertEquals(product1.getUuid(), products.get(0).getUuid());
        assertEquals(product2.getUuid(), products.get(1).getUuid());

    }

}
