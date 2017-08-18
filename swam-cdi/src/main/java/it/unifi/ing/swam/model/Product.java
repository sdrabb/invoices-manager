package it.unifi.ing.swam.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String productName;

    public Product() {
        super();
    }

    public Product(String uuid) {
        super(uuid);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}
