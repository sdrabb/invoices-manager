package it.unifi.ing.swam.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class InfoOrder implements Serializable {

    private int quantity;

    private float subTotal;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

}
