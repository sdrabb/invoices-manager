package it.unifi.ing.swam.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_orders")
public class InvoiceOrder extends BaseEntity {

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Product product;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "quantity", column = @Column(name = "quantity_product"))
        ,
	    @AttributeOverride(name = "subTotal", column = @Column(name = "sub_total"))
    })
    private InfoOrder infoOrder;

    public InvoiceOrder() {
    }

    public InfoOrder getInfoOrder() {
        return infoOrder;
    }

    public void setInfoOrder(InfoOrder infoOrder) {
        this.infoOrder = infoOrder;
    }

    public InvoiceOrder(String uuid) {
        super(uuid);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
