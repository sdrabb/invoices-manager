package it.unifi.ing.swam.dao;

import it.unifi.ing.swam.model.Product;
import java.util.List;

public class ProductDao extends BaseDao<Product> {

    public ProductDao() {
        super(Product.class);
    }

    public List<Product> getProducts() {
        String query = "SELECT p FROM Product p";

        List<Product> products = entityManager
                .createQuery(query, Product.class)
                .getResultList();

        return products;
    }

}
